package com.example.reto1.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import androidx.fragment.app.Fragment
import com.example.reto1.R
import com.example.reto1.databinding.ActivityMainBinding
import com.example.reto1.model.Evento
import com.example.reto1.model.Restaurante
import com.example.reto1.util.EventoAdapter

class MainActivity : AppCompatActivity() {


    //PERFIL
    private lateinit var perfil: Perfil
    private lateinit var listaEventos: ListaEventos

    private lateinit var vistaPerfil: VistaPerfil

    //PUBLICACIONES
    private lateinit var publicacionesVacias: PublicacionesVacias
    private lateinit var publicacionNueva: PublicacionNueva

    private lateinit var binding : ActivityMainBinding

    //STATE
    private val restaurantes = ArrayList<Restaurante>()
    private val eventosAdapter = EventoAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)

        //PERFILES
        perfil = Perfil.newInstance()
        listaEventos = ListaEventos.newInstance()

        vistaPerfil = VistaPerfil.newInstance()


        //PUBLICACIONES
        publicacionesVacias = PublicacionesVacias.newInstance()
        publicacionNueva = PublicacionNueva.newInstance()
        publicacionNueva.setListaEventos(listaEventos)
        listaEventos.setAdapter(eventosAdapter)


        showFragment(vistaPerfil)

        binding.navigator.setOnItemSelectedListener { menuItem ->
            if (menuItem.itemId == R.id.perfil){
                showFragment(vistaPerfil)

            } else if (menuItem.itemId == R.id.publicaciones){
                if (eventosAdapter.getItemCount()  == 0){
                    showFragment(publicacionesVacias)

                } else{
                    showFragment(listaEventos)
                }

            } else if (menuItem.itemId == R.id.mapa){
                showFragment(listaEventos)
                //CHANGE THIS TO MAP TODO
            } else{
                showFragment(vistaPerfil)
            }
            true

        }
    }

    fun showFragment(fragment : Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.commit()


    }

    //MANEJO ESTADO DE EVENTOS
    fun addEvento(newEvento: Evento){
        eventosAdapter.addEvento(newEvento)
    }

    //MANEJO ESTADO DE RESTAURANTES
    fun addRestaurante(newRestaurante: Restaurante){
        val posicionRestaurante = findRestaurantByName(newRestaurante.nombre)

        if (posicionRestaurante == null){
            restaurantes.add(newRestaurante)
        } else{
            val auxRestaurante = restaurantes[posicionRestaurante-1]
            restaurantes.add(auxRestaurante)
            restaurantes.removeAt(posicionRestaurante-1)
        }

    }

    fun getSizeRestaurantes():Int{
        return restaurantes.size
    }

    fun getRestauranteByIndex(pos : Int): Restaurante {
        return restaurantes[pos]
    }


    fun findRestaurantByName(name: String): Int? {
        var counter = 0
        for (restaurante in restaurantes) {
            counter++
            if (restaurante.nombre.compareTo(name,true) == 0) {
                return counter
            }
        }
        return null
    }



} //end of class