package com.example.reto1.view

import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.reto1.R
import com.example.reto1.databinding.ActivityMainBinding
import com.example.reto1.model.Evento
import com.example.reto1.model.Restaurante
import com.example.reto1.util.EventoAdapter
import com.example.reto1.util.Request
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {


    //PERFIL
    private lateinit var perfil: Perfil
    private lateinit var listaEventos: ListaEventos

    private lateinit var vistaPerfil: VistaPerfil

    //PUBLICACIONES
    private lateinit var publicacionesVacias: PublicacionesVacias
    private lateinit var publicacionNueva: PublicacionNueva

    private lateinit var binding : ActivityMainBinding

    //MAPA EVENTOS
    private lateinit var mapasEventos: MapaEventos

    //STATE
    private var restaurantes = ArrayList<Restaurante>()
    private var eventosAdapter = EventoAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        requestPermissions(arrayOf(
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ), 1)

        //PERFILES
        perfil = Perfil.newInstance()
        listaEventos = ListaEventos.newInstance()

        vistaPerfil = VistaPerfil.newInstance()


        //PUBLICACIONES
        publicacionesVacias = PublicacionesVacias.newInstance()
        publicacionNueva = PublicacionNueva.newInstance()

        //MAPA EVENTOS
        mapasEventos = MapaEventos()



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
                showFragment(mapasEventos)

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

    //PERSISTENCIA

//    override fun onPause() {
//        super.onPause()
//        val request = Request(restaurantes, eventosAdapter)
//
//        //Serializar
//        val json = Gson().toJson(request)
//        Log.e(">>>",json)
//
//        //Shared Preferences
//        val sharedPref = getPreferences(Context.MODE_PRIVATE)
//        sharedPref.edit().putString("currentState", json).apply()
//
//
//    }
//
//    override fun onResume() {
//        super.onResume()
//
//        val sharedPref = getPreferences(Context.MODE_PRIVATE)
//        val json = sharedPref.getString("currentState", "NO_DATA")
//
//        if (json != "NO_DATA"){
//            //Deserializar
//            val currentState = Gson().fromJson(json, Request::class.java)
//
//            //TODO
////            restaurantes = currentState.restaurantes
////            eventosAdapter = currentState.eventos
//
//        }
//
//
//    }

    //Aqui debí usar generics pero el time no me dio

    fun arrayListRestaurantesToString(array:ArrayList<Restaurante>):String{
        var arrayString:String = ""

        for (restaurant in array){

        }

        return arrayString
    }

    fun arrayListEventosToString(array:ArrayList<Evento>){

    }



    //MANEJO DE PERMISOS

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        var allGranted = true
        for (result in grantResults){
            if (result == PackageManager.PERMISSION_DENIED){
                allGranted = false
                break
            }

        }

        if (allGranted == false){
            Toast.makeText(this, "Error: Cerrando aplicación, por favor acepte los permisos solicitados", Toast.LENGTH_LONG).show()
            Thread.sleep(2000)
            finishAffinity()
        }
    }

    //MANEJO ESTADO DE EVENTOS
    fun addEvento(newEvento: Evento){
        eventosAdapter.addEvento(newEvento)
    }

    fun getEventAdapter(): EventoAdapter {
        return eventosAdapter
    }

    //MANEJO ESTADO DE RESTAURANTES

    fun getRestauranteActual():Restaurante?{
        if (getSizeRestaurantes() >0){
            return restaurantes[getSizeRestaurantes()-1]
        }
        return null
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