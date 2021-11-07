package com.example.reto1.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.reto1.R
import com.example.reto1.databinding.ActivityMainBinding
import com.example.reto1.model.Perfil
import com.example.reto1.model.Restaurante

class MainActivity : AppCompatActivity() {



    private lateinit var perfil: Perfil
    private lateinit var listaEventos: ListaEventos

    private lateinit var vistaPerfil: VistaPerfil

    private lateinit var binding : ActivityMainBinding

    //STATE
    private val restaurantes = ArrayList<Restaurante>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)

        perfil = Perfil.newInstance()
        listaEventos = ListaEventos.newInstance()

        vistaPerfil = VistaPerfil.newInstance()

        //Suscripcion
        perfil.listener = listaEventos



        showFragment(vistaPerfil)

        binding.navigator.setOnItemSelectedListener { menuItem ->
            if (menuItem.itemId == R.id.perfil){
                showFragment(vistaPerfil)
                vistaPerfil.setPerfil(perfil)
            } else if (menuItem.itemId == R.id.publicaciones){
                showFragment(listaEventos)
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
        transaction.addToBackStack(null)
        transaction.commit()

    }

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