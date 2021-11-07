package com.example.reto1.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.reto1.R
import com.example.reto1.databinding.FragmentVistaPerfilBinding
import com.example.reto1.model.Perfil
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [VistaPerfil.newInstance] factory method to
 * create an instance of this fragment.
 */
class VistaPerfil : Fragment(){

    private  var _binding: FragmentVistaPerfilBinding? = null
    private val binding get() = _binding!!

    private lateinit var perfil: Perfil



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        perfil = Perfil.newInstance()
    }

    fun setPerfil(perfilAnterior : Perfil){
        perfil = perfilAnterior
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e(">>>","onCreateView")
        // Inflate the layout for this fragment

        _binding = FragmentVistaPerfilBinding.inflate(inflater, container, false)
        val view = binding.root

        //RECUPERAR EL ESTADO
        val activity: MainActivity = context as MainActivity
        Log.e(">>>>",activity.getSizeRestaurantes().toString())

        if (activity.getSizeRestaurantes() > 0) {
            Log.e(">>>>","LLEGUEEEEEEEEE")
            var restaurante = activity.getRestauranteByIndex(activity.getSizeRestaurantes()-1)

            binding.nombreRestaurante.text = restaurante.nombre
            binding.descripcionRestaurante.text = restaurante.descripcion
        }


        binding.editarBtn.setOnClickListener {
            Log.e(">>>", requireActivity().toString())
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, perfil)
            transaction.addToBackStack("vistaPerfil")
            transaction.commit()

        }


        return view
    }



    override fun onDestroyView() {
        Log.e(">>>","onDestroyView")
        super.onDestroyView()
        _binding = null
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
        fun newInstance() = VistaPerfil()

    }


} //end of class