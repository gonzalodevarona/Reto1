package com.example.reto1.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.reto1.R
import com.example.reto1.model.Evento

class EventoAdapter : RecyclerView.Adapter<EventoViewHolder>() {

    private var eventos = ArrayList<Evento>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoViewHolder {
        //Inflate: XML -> View
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.eventorow, parent, false)
        val eventoViewHolder = EventoViewHolder(view)
        return eventoViewHolder
    }

    override fun onBindViewHolder(holder: EventoViewHolder, position: Int) {
        val eventon = eventos[position]

        holder.nombreEvento.text = eventon.nombreEvento
        holder.nombreRestaurante.text = eventon.nombreRestaurante
        holder.fechaInicio.text = eventon.fechaInicio
        holder.fechaFin.text = eventon.fechaFin

    }

    override fun getItemCount(): Int {
        return eventos.size
    }

    fun addEvento(evento: Evento) {
        eventos.add(evento)

    }

    fun getEventos(): ArrayList<Evento> {
        return eventos
    }

    fun setEventos(newEventos : ArrayList<Evento>) {
        eventos = newEventos
    }

} //end of class