package com.dbsrm.valeohackathonproject

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_speech_to_text_recog.*
import java.util.*

class SpeechToTextRecog : AppCompatActivity() {

    private val REQUEST_CODE_SPEECH_INPUT = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speech_to_text_recog)

        speechrecogbtn.setOnClickListener {
            speak()
        }
    }

    private fun speak() {
        val mIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        mIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        mIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        mIntent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Speak Up Something!")

        try {
            startActivityForResult(mIntent,REQUEST_CODE_SPEECH_INPUT)

        }catch (e: Exception){
            Toast.makeText(this,e.message,Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int,resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode,resultCode, data)
        when(requestCode){
            REQUEST_CODE_SPEECH_INPUT ->{
                if(resultCode == Activity.RESULT_OK && null != data){
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    recogText.text = result[0]
                    checkRecogText(result[0])
                }
            }
        }
    }

    fun checkRecogText(result: String) {
        var str1: String = "Hello."
        var str2: String = "move left"
        var str3: String = "move right"
        var str4: String = "move back"

        if(str1.equals(result)||str2.equals(result)||str3.equals(result)||str4.equals(result)){
            Toast.makeText(this,"OK",Toast.LENGTH_LONG).show()
            startActivity(Intent(this,Bluetooth::class.java))

        }
        else{
            Toast.makeText(this,"error Try Again!",Toast.LENGTH_LONG).show()
        }

    }
}
