package com.example.reto1.util

import androidx.fragment.app.Fragment
import com.example.reto1.R
import com.example.reto1.view.PublicacionNueva

open class GeneralBehavior : Fragment() {

    fun emptyBackStack(){
        val fm = requireActivity().supportFragmentManager
        var i = fm.backStackEntryCount
        while (i<0){
            fm.popBackStack()
        }
    }

    fun changeFromFragmentAtoFragmentB(fragmentB : Fragment){
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragmentB)
        transaction.commit()
    }

    fun changeFromFragmentAtoFragmentBWithBackstack(fragmentB : Fragment, myBackstack : String?){
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragmentB)
        transaction.addToBackStack(myBackstack)
        transaction.commit()
    }


} //end of class