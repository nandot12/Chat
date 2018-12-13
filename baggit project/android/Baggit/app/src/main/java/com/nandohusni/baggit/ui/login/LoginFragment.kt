@file:Suppress("PLUGIN_WARNING")

package com.nandohusni.baggit.ui.login

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nandohusni.baggit.R
import com.nandohusni.baggit.ui.mainutama.MainUtama
import com.nandohusni.baggit.ui.signup.SignUpActivity
import com.nandohusni.baggit.utils.SessionManager
import kotlinx.android.synthetic.main.login_fragment.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class LoginFragment : Fragment() {

    private var sesi: SessionManager? = null

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    private fun initView() {

        sesi = activity?.let { SessionManager(it) }

        textSignUp.setOnClickListener {
            startActivity<SignUpActivity>()
        }

        btnSignin.setOnClickListener {

            if (loginEmail.text.toString().isNotEmpty() && loginPassword.text.toString().isNotEmpty()) {

                viewModel.sigIn(loginEmail.text.toString(), loginPassword.text.toString()).observe(this, Observer {


                        t ->

                    if (t != null) {


                        sesi?.createLoginSession(getString(R.string.one))



                        sesi?.idUser = t.userId
                        sesi?.email = loginEmail.text.toString()



                        startActivity<MainUtama>()
                    }
                })

            } else {
                toast(getString(R.string.check_valid))
            }


        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val factory = activity?.application?.let {
            LoginViewModel.Factory(
                it
            )
        }
        viewModel = ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)
    }

}
