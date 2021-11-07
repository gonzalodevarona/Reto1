package com.example.reto1.view

import android.app.Activity.MODE_ENABLE_WRITE_AHEAD_LOGGING
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import com.example.reto1.databinding.FragmentPerfilBinding
import com.example.reto1.model.Restaurante
import com.example.reto1.util.GeneralBehavior
import androidx.activity.result.ActivityResult as ActivityResult


class Perfil :  GeneralBehavior() {

    private  var _binding: FragmentPerfilBinding? = null
    private val binding get() = _binding!!

    //Listener
    var listener : OnNewEventoListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment

        _binding = FragmentPerfilBinding.inflate(inflater, container, false)
        val view = binding.root

        val galleryLauncher = registerForActivityResult(StartActivityForResult(), ::onGalleryResult)


        //RECUPERAR EL ESTADO
        val activity: MainActivity = context as MainActivity


        if (activity.getSizeRestaurantes() > 0) {

            var restaurante = activity.getRestauranteByIndex(activity.getSizeRestaurantes()-1)

            binding.nameRestaurant.append(restaurante.nombre)
            binding.descriptionRestaurant.append(restaurante.descripcion)
        }

        //Editar restaurante accion
        binding.addRestaurantBtn.setOnClickListener {
            val text = binding.nameRestaurant.text.toString()

            //Publicacion
            listener?.let {
                it.onNewEvento(text)
            }



            //Publicacion perfil
            var ableToCreateProfile = true

            if(binding.nameRestaurant.text.toString().compareTo("") == 0 && binding.descriptionRestaurant.text.toString().compareTo("") == 0){
                if (binding.nameRestaurant.text.toString().compareTo("") == 0){
                    Toast.makeText(activity, "Error: No has escrito el nombre del restaurante", Toast.LENGTH_LONG).show()
                }

                if (binding.descriptionRestaurant.text.toString().compareTo("") == 0){
                    Toast.makeText(activity, "Error: No has escrito la descripción del restaurante", Toast.LENGTH_LONG).show()
                }
                ableToCreateProfile = false
            } else{
                ableToCreateProfile = true
            }


            Log.e(">>>", "afuera estoy ")

            if (ableToCreateProfile){
                Log.e(">>>", "aqui estoy marica")
                val activity: MainActivity = context as MainActivity
                val restaurante =  Restaurante(binding.nameRestaurant.text.toString(), binding.descriptionRestaurant.text.toString(), binding.descriptionRestaurant.text.toString())
                activity.addRestaurante(restaurante)
                val transaction = requireActivity().supportFragmentManager.popBackStack()


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
        super.onDestroyView()
        _binding = null
    }

    interface OnNewEventoListener{
        fun onNewEvento(evento : String){}
    }



    companion object {

        @JvmStatic
        fun newInstance() = Perfil()

    }
}