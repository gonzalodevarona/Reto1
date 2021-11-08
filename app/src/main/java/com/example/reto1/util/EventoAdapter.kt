package com.example.reto1.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.reto1.R
import com.example.reto1.model.Evento

class EventoAdapter : RecyclerView.Adapter<EventoViewHolder>() {

    private val eventos = ArrayList<Evento>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoViewHolder {
        //Inflate: XML -> View
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.eventorow, parent, false)
        val eventoViewHolder = EventoViewHolder(view)
        return eventoViewHolder
    }

    override fun onBindViewHolder(holder: EventoViewHolder, position: Int) {
        val eventon = eventos[position]
        //TODO
       // holder.eventotextrow.text = eventon.evento
    }

    override fun getItemCount(): Int {
        return eventos.size
    }

    fun addEvento(evento: Evento) {
        eventos.add(evento)

    }
}