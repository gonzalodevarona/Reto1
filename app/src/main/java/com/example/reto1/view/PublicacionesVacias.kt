package com.example.reto1.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.reto1.R
import com.example.reto1.databinding.FragmentPublicacionesVaciasBinding
import com.example.reto1.util.GeneralBehavior


class PublicacionesVacias :  GeneralBehavior() {

    private  var _binding: FragmentPublicacionesVaciasBinding? = null
    private val binding get() = _binding!!

    //Siguiente fragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPublicacionesVaciasBinding.inflate(inflater, container, false)
        val view = binding.root



        binding.crearPublicacionBtn.setOnClickListener {

            val activity: MainActivity = context as MainActivity

            if(activity.getSizeRestaurantes() >0){
                super.changeFromFragmentAtoFragmentBWithBackstack(PublicacionNueva.newInstance(), "publicacionesVacias")
            } else{
                Toast.makeText(activity, "Error: No hay un restaurante asociado para crear un evento", Toast.LENGTH_LONG).show()
            }
        }

        return view
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = PublicacionesVacias()

    }
}