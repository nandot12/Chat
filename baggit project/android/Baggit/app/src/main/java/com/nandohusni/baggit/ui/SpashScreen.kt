package com.nandohusni.baggit.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.nandohusni.baggit.R
import com.nandohusni.baggit.ui.login.loginActivity
import com.nandohusni.baggit.ui.mainutama.MainUtama
import com.nandohusni.baggit.ui.signup.SignUpActivity
import com.nandohusni.baggit.utils.SessionManager
import org.jetbrains.anko.startActivity

class SpashScreen : AppCompatActivity() {

    private var sesi : SessionManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spash_screen)

        sesi = SessionManager(this)



        Handler().postDelayed({

           if(sesi?.isLoggedIn!!){
               startActivity<MainUtama>()
           }else startActivity<loginActivity>()

            finish()

        },4000)
    }
}
