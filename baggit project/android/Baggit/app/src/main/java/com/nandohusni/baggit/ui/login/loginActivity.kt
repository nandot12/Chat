package com.nandohusni.baggit.ui.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nandohusni.baggit.R
import com.nandohusni.baggit.ui.signup.SignUpActivity
import kotlinx.android.synthetic.main.login_fragment.*
import org.jetbrains.anko.startActivity

class loginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment.newInstance())
                .commitNow()
        }

    }



}
