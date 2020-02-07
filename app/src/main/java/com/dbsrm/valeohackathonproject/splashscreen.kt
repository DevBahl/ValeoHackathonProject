package com.dbsrm.valeohackathonproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash_screen.*

class splashscreen : AppCompatActivity() {

    override fun onDestroy() {
        super.onDestroy()
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        imageView4.startAnimation(AnimationUtils.loadAnimation(this,R.anim.splash_in))
        Handler().postDelayed({
            imageView4.startAnimation(AnimationUtils.loadAnimation(this,R.anim.splash_out))
            Handler().postDelayed({
                imageView4.visibility = View.GONE
                startActivity(Intent(this,walkthrmain::class.java))
                finish()
            },500)
        },1200)
    }
}
