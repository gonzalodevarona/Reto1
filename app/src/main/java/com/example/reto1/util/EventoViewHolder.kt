package com.example.reto1.util

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.reto1.R

class EventoViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    //UI Controllers
    var nombreEvento:TextView = itemView.findViewById(R.id.nombreEvento)
    var nombreRestaurante:TextView = itemView.findViewById(R.id.nombreRestaurante)
    var fechaInicio:TextView = itemView.findViewById(R.id.fechaInicio)
    var fechaFin:TextView = itemView.findViewById(R.id.fechaFin)
    var direccionEvento:TextView = itemView.findViewById(R.id.direccionEvento)
    //State

    init {

    }
}