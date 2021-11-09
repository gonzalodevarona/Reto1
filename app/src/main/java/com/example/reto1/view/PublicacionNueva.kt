package com.example.reto1.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.reto1.R
import com.example.reto1.databinding.FragmentPublicacionNuevaBinding
import com.example.reto1.databinding.FragmentPublicacionesVaciasBinding
import com.example.reto1.model.Evento
import com.example.reto1.model.Restaurante
import com.example.reto1.util.EventoAdapter
import com.example.reto1.util.GeneralBehavior
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*


class PublicacionNueva :  GeneralBehavior() {

    private  var _binding: FragmentPublicacionNuevaBinding? = null
    private val binding get() = _binding!!

    //Proximo Fragment
    private var listaEventos : ListaEventos? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPublicacionNuevaBinding.inflate(inflater, container, false)
        val view = binding.root

        var ableToCreateEvent = true

        //ACCION BOTON CREAR
        binding.crearBtn.setOnClickListener {
            if (binding.nombreEvento.text.toString() == "" && binding.horaInicioBtn.text.toString() == "Inicio" && binding.horaFinBtn.text.toString() == "Fin"){
                if (binding.nombreEvento.text.toString() == ""){
                    Toast.makeText(activity, "Error: No has escrito el nombre del evento", Toast.LENGTH_LONG).show()
                }

                if (binding.horaInicioBtn.text.toString() == "Inicio"){
                    Toast.makeText(activity, "Error: No has seleccionado la fecha de inicio del evento", Toast.LENGTH_LONG).show()
                }

                if (binding.horaFinBtn.text.toString() == "Fin"){
                    Toast.makeText(activity, "Error: No has seleccionado la fecha de fin del evento", Toast.LENGTH_LONG).show()
                }

                ableToCreateEvent = false
            }else{
                ableToCreateEvent = true
            }


            if (ableToCreateEvent){


                val activity: MainActivity = context as MainActivity
                val restauranteAsociado = activity.getRestauranteActual()

                val publicacion =  Evento(restauranteAsociado!!.nombre, binding.nombreEvento.text.toString(), binding.horaInicioBtn.text.toString(), binding.horaFinBtn.text.toString())
                activity.addEvento(publicacion)



                super.changeFromFragmentAtoFragmentB(ListaEventos.newInstance())

            }

        }

        //ACCION BOTON INICIO
        binding.horaInicioBtn.setOnClickListener {
            actionDate(binding.horaInicioBtn)
        }

        //ACCION BOTON FIN
        binding.horaFinBtn.setOnClickListener {
            actionDate(binding.horaFinBtn)
        }

        return view
    }

    private fun actionDate(btnText : Button){
        var formate = SimpleDateFormat("dd/MMM/YYYY",Locale.US)
        var timeFormat = SimpleDateFormat("hh:mm a", Locale.US)

        val now = Calendar.getInstance()
        val datePicker = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(Calendar.YEAR,year)
            selectedDate.set(Calendar.MONTH,month)
            selectedDate.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            var date = formate.format(selectedDate.time)


            //HORA
            val timePicker = TimePickerDialog(requireContext(), TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                val selectedTime = Calendar.getInstance()
                selectedTime.set(Calendar.HOUR_OF_DAY,hourOfDay)
                selectedTime.set(Calendar.MINUTE,minute)
                date = date+" "+timeFormat.format(selectedTime.time)


                btnText.text = date
            },
                now.get(Calendar.HOUR_OF_DAY),now.get(Calendar.MINUTE),false)
            timePicker.show()

        },
            now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH))
        datePicker.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = PublicacionNueva()

    }
}