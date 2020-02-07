package com.dbsrm.valeohackathonproject

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_secondwalkthrfragment.*

class secondwalkthrfragment:Fragment() {

    companion object {
        fun newInstance() = secondwalkthrfragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_secondwalkthrfragment,container,false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        next.setOnClickListener{
            val intent = Intent(context,MainActivity::class.java)
            startActivity(intent)
        }
    }
}