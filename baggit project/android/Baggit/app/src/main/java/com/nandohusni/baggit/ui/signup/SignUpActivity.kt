package com.nandohusni.baggit.ui.signup

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nandohusni.baggit.R

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SignUpFragment.newInstance())
                .commitNow()
        }
    }

}
