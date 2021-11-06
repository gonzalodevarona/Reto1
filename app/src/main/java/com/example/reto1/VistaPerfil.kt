package com.example.reto1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.reto1.databinding.FragmentPerfilBinding


/**
 * A simple [Fragment] subclass.
 * Use the [VistaPerfil.newInstance] factory method to
 * create an instance of this fragment.
 */
class VistaPerfil : Fragment() {

    private  var _binding: FragmentPerfilBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e(">>>","onCreateView")
        // Inflate the layout for this fragment

        _binding = FragmentPerfilBinding.inflate(inflater, container, false)
        val view = binding.root

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
         * @return A new instance of fragment Perfil.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = VistaPerfil()

    }
}