package com.dbsrm.valeohackathonproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sendbtn.setOnClickListener {
            val intent = Intent(this,SpeechToTextRecog::class.java)
            startActivity(intent)
        }
        receivebtn.setOnClickListener {
            val intent = Intent(this,bluetoothReceive::class.java)
            startActivity(intent)
        }
    }
}
