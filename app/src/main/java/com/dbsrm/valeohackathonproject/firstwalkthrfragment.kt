package com.dbsrm.valeohackathonproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class firstwalkthrfragment:Fragment() {

    companion object {
        fun newInstance() = firstwalkthrfragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_firstwalkthrfragment,container,false)
    }
}
