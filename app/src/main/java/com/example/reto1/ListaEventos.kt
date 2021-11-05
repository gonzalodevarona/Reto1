package com.example.reto1

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reto1.Perfil.OnNewEventoListener
import com.example.reto1.databinding.FragmentListaEventosBinding
import java.util.*


class ListaEventos : Fragment(), Perfil.OnNewEventoListener {


    private  var _binding:FragmentListaEventosBinding? = null
    private val binding get() = _binding!!

    //_binding -> nulleable
    //binding -> no nulleable



    //STATE
    private val adapter = EventoAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e(">>>","onCreateView")
        // Inflate the layout for this fragment

        _binding = FragmentListaEventosBinding.inflate(inflater, container, false)
        val view = binding.root


        //Recrear el estado
        val eventoRecycler = binding.eventoRecycler
        eventoRecycler.setHasFixedSize(true)
        eventoRecycler.layoutManager = LinearLayoutManager(activity)
        eventoRecycler.adapter = adapter


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
         * @return A new instance of fragment ListaEventos.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = ListaEventos()

    }

    //Metodo que se ejecuta desde Perfil
    override fun onNewEvento(evento:String) {
        //Modificar el estado
        val newEvento = Evento(UUID.randomUUID().toString(), evento)
        adapter.addEvento(newEvento)

    }
}