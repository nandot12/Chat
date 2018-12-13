package com.nandohusni.baggit.ui.signup

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.nandohusni.baggit.R
import com.nandohusni.baggit.ui.login.loginActivity
import kotlinx.android.synthetic.main.sign_up_fragment.*
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.yesButton
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.nandohusni.baggit.ui.mainutama.MainUtama
import com.nandohusni.baggit.utils.SessionManager


class SignUpFragment : Fragment() {

    private var sesi: SessionManager? = null
    private var client: GoogleSignInClient? = null
    private val RC_SIGN_IN = 1

    companion object {
        fun newInstance() = SignUpFragment()
    }

    private lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.sign_up_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory = activity?.application?.let {
            SignUpViewModel.SignUpViewModelFactory(
                it
            )
        }

        sesi = context?.let { SessionManager(it) }
        viewModel = ViewModelProviders.of(this, factory).get(SignUpViewModel::class.java)


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initGmail()

        btnSignUp.setOnClickListener {

            if (signUpName.text.isNotEmpty() && signPassword.text.toString().isNotEmpty() &&
                signPasswordConf.text.toString().isNotEmpty() &&
                signUpEmail.text.toString().isNotEmpty()
            ) {

                viewModel.loadSignUp(
                    signUpName.text.toString(), signUpEmail.text.toString(),
                    signPassword.text.toString(), getString(R.string.one)
                ).observe(this, Observer { t ->


                    if (t != null) {

                        if (t.resulttext == getString(R.string.success_signup)) {

                            showalert()
                        } else {
                            toast(t.resulttext.toString())
                        }
                    }


                })

            } else {
                toast(getString(R.string.check_valid))
            }


        }

    }

    private fun initGmail() {


        val gson = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail().build()
        client = context?.let { GoogleSignIn.getClient(it, gson) }

        btnGmail.setOnClickListener {
            val signInIntent = client?.getSignInIntent()

            startActivityForResult(signInIntent, RC_SIGN_IN)
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(task: Task<GoogleSignInAccount>?) {


        val email = task?.result?.email
        val name = task?.result?.displayName

        name?.let {
            email?.let { it1 ->
                viewModel.loadSignUp(
                    it,
                    it1, "", getString(R.string.one)
                ).observe(this, Observer {

                        t ->


                    if (t != null) {

                        if (t.resulttext == getString(R.string.success_signup)) {


                            sesi?.createLoginSession(getString(R.string.one))
                            sesi?.idUser = t.userId
                            sesi?.nama = name
                            sesi?.email = email

                            moves()
                        } else {


                            sesi?.createLoginSession(getString(R.string.one))
                            sesi?.idUser = t.userId
                            sesi?.token = t.token
                            sesi?.nama = name
                            sesi?.email = email

                            moves()


                        }


                    }
                })
            }
        }


    }

    private fun moves() {

        startActivity<MainUtama>()
    }

    private fun showalert() {

        alert {
            message = getString(R.string.verification)
            title = getString(R.string.informasi)
            yesButton {

                startActivity<loginActivity>()

            }

        }
            .show()
    }


}
