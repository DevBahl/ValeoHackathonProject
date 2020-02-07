package com.dbsrm.valeohackathonproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        sendbtn.setOnClickListener {
            val intent = Intent(this,SpeechToTextRecog::class.java)
            startActivity(intent)
        }
        receivebtn.setOnClickListener {
            val intent = Intent(this,bluetoothReceive::class.java)
            startActivity(intent)
        }

        lutBtn.setOnClickListener {
            startActivity(Intent(this,LUT::class.java))
        }
    }
}
