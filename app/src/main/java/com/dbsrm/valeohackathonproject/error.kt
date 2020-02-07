package com.dbsrm.valeohackathonproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_error.*

class error : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error)

        backbtn1.setOnClickListener {
            startActivity(Intent(this,SpeechToTextRecog::class.java))
        }
    }
}
