package com.example.reto1.view

import android.annotation.SuppressLint
import android.content.Context.LOCATION_SERVICE
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import com.example.reto1.R
import com.example.reto1.databinding.FragmentEscogerMapaBinding
import com.example.reto1.databinding.FragmentMapaEventosBinding

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener
import java.util.*


class EscogerMapa : Fragment() {

    private  var _binding: FragmentEscogerMapaBinding? = null
    private val binding get() = _binding!!

    private lateinit var  mMap:GoogleMap
    private lateinit var manager : LocationManager

    private var address : Marker? = null

    //Listener
    var listener : OnNewMarkerListener? = null

    private val callback = OnMapReadyCallback { googleMap ->
        mMap = googleMap
        listener = PublicacionNueva()
        setInitialPos()

        //Operaciones


        mMap.setOnMarkerDragListener(object : OnMarkerDragListener {
            override fun onMarkerDragStart(marker: Marker) {
                // TODO Auto-generated method stub
            }

            override fun onMarkerDragEnd(marker: Marker) {
                // TODO Auto-generated method stub
                var lat = marker.position.latitude
                var lng = marker.position.longitude

                editMarkerPosition(lat, lng)
            }

            override fun onMarkerDrag(marker: Marker) {
                // TODO Auto-generated method stub
            }
        })

        binding.addDireccion.setOnClickListener{

            listener?.let{
                if(address != null){
                    it.onNewMarker(address!!)
                }

            }

            //if(listener !=null) (listener as PublicacionNueva).onNewMarker(address!!)

            val transaction = requireActivity().supportFragmentManager.popBackStack()
        }



    }

    fun putNewMarker(lat:Double, lng:Double):Marker?{
        val pos = LatLng(lat,lng)
        val marker = mMap.addMarker(MarkerOptions().position(pos).title("Ubicación seleccionada").draggable(true))
        mMap.animateCamera(CameraUpdateFactory.newLatLng(pos))
        address = marker


        return marker

    }

    fun editMarkerPosition(lat:Double, lng:Double){
        val pos = LatLng(lat,lng)
        address?.position = pos
        mMap.animateCamera(CameraUpdateFactory.newLatLng(pos))

    }

    @SuppressLint("MissingPermission")
    fun setInitialPos(){
        val pos = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        if(pos != null){
            putNewMarker(pos.latitude, pos.longitude)
        } else{
            putNewMarker(3.3598, -76.5222)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e(">>>","onCreateView")
        // Inflate the layout for this fragment

        _binding = FragmentEscogerMapaBinding.inflate(inflater, container, false)
        val view = binding.root

        manager = requireActivity().getSystemService(LOCATION_SERVICE) as LocationManager

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)


    }

    interface OnNewMarkerListener{
        fun onNewMarker(marker: Marker)
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = EscogerMapa()

    }
} //end of class