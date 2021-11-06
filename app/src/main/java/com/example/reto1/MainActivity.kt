package com.example.reto1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.reto1.databinding.ActivityMainBinding
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {



    private lateinit var perfil: Perfil
    private lateinit var listaEventos: ListaEventos

    private lateinit var vistaPerfil: VistaPerfil

    private lateinit var binding : ActivityMainBinding


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
        transaction.commit()

    }
}