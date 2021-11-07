package com.example.reto1.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reto1.util.EventoAdapter
import com.example.reto1.databinding.FragmentListaEventosBinding
import com.example.reto1.model.Evento
import com.example.reto1.util.GeneralBehavior
import java.util.*


class ListaEventos :   GeneralBehavior(), Perfil.OnNewEventoListener {


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
        val fm = requireActivity().supportFragmentManager
        var i = fm.backStackEntryCount
        while (i<0){
            fm.popBackStack()
        }


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