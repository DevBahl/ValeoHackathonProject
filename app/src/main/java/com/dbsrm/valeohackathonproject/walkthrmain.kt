package com.dbsrm.valeohackathonproject


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_walkthrmain.*


class walkthrmain : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walkthrmain)

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val pagerAdapter = walkthrfragmentpageradapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

    }

    private fun selectDrawerItem(item: MenuItem) {
        when (item.itemId) {
            R.id.fragmentfirstimage -> viewPager.currentItem = 0
            R.id.fragmentsecondimage -> viewPager.currentItem = 1
            else -> viewPager.currentItem = 0
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

}