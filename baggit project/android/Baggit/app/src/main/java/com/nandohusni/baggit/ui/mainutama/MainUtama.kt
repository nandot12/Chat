package com.nandohusni.baggit.ui.mainutama

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.nandohusni.baggit.R
import com.nandohusni.baggit.ui.home.HomeFragment
import com.nandohusni.baggit.ui.profile.ProfilFragment
import kotlinx.android.synthetic.main.activity_main_utama.*

class MainUtama : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {

                supportFragmentManager.beginTransaction().replace(R.id.container,HomeFragment()).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
             //   supportFragmentManager.beginTransaction().replace(R.id.container,HomeFragment()).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.profile -> {
                supportFragmentManager.beginTransaction().replace(R.id.container,ProfilFragment()).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_utama)

        supportFragmentManager.beginTransaction().replace(R.id.container,HomeFragment()).commit()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
