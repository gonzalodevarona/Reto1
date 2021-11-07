package com.example.reto1.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.reto1.R
import com.example.reto1.databinding.FragmentPublicacionNuevaBinding
import com.example.reto1.databinding.FragmentPublicacionesVaciasBinding
import com.example.reto1.util.GeneralBehavior


class PublicacionNueva :  GeneralBehavior() {

    private  var _binding: FragmentPublicacionNuevaBinding? = null
    private val binding get() = _binding!!

    //Siguiente fragment
    private lateinit var listaEventos: ListaEventos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPublicacionNuevaBinding.inflate(inflater, container, false)
        val view = binding.root

        var ableToCreateEvent = true
        binding.crearBtn.setOnClickListener {
            if (binding.nombreEvento.text.toString() == ""){
                Toast.makeText(activity, "Error: No has escrito el nombre del evento", Toast.LENGTH_LONG).show()
                ableToCreateEvent = false
            }else{
                ableToCreateEvent = true
            }

            if (ableToCreateEvent){
                val transaction = requireActivity().supportFragmentManager.popBackStack()
                super.changeFromFragmentAtoFragmentB(ListaEventos.newInstance())

            }

        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = PublicacionNueva()

    }
}