package com.example.reto1

import android.app.Activity.RESULT_OK
import android.app.Instrumentation
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import com.example.reto1.databinding.FragmentListaEventosBinding
import com.example.reto1.databinding.FragmentPerfilBinding
import androidx.activity.result.ActivityResult as ActivityResult


/**
 * A simple [Fragment] subclass.
 * Use the [Perfil.newInstance] factory method to
 * create an instance of this fragment.
 */
class Perfil : Fragment() {

    private  var _binding: FragmentPerfilBinding? = null
    private val binding get() = _binding!!

    //Listener
    var listener : OnNewEventoListener? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e(">>>","onCreateView")
        // Inflate the layout for this fragment

        _binding = FragmentPerfilBinding.inflate(inflater, container, false)
        val view = binding.root

        val galleryLauncher = registerForActivityResult(StartActivityForResult(), ::onGalleryResult)


        binding.addRestaurantBtn.setOnClickListener {
            val text = binding.nameRestaurant.text.toString()
            Toast.makeText(activity, text, Toast.LENGTH_LONG).show()

            //Publicacion
            listener?.let {
                it.onNewEvento(text)
            }
        }

        binding.galleryBtn.setOnClickListener{
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            galleryLauncher.launch(intent)
        }

        return view
    }


    fun onGalleryResult(result: ActivityResult){
        if (result.resultCode == RESULT_OK){
            val uriImage = result.data?.data
            uriImage?.let {
                binding.galleryBtn.setImageURI(uriImage)
            }
        }
    }

    override fun onDestroyView() {
        Log.e(">>>","onDestroyView")
        super.onDestroyView()
        _binding = null
    }

    interface OnNewEventoListener{
        fun onNewEvento(evento : String){}
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Perfil.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = Perfil()

    }
}