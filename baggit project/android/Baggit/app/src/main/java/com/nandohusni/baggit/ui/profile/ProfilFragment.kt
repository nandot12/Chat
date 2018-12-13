package com.nandohusni.baggit.ui.profile

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.nandohusni.baggit.R
import com.nandohusni.baggit.ui.login.loginActivity
import com.nandohusni.baggit.utils.SessionManager
import kotlinx.android.synthetic.main.profil_fragment.*
import org.jetbrains.anko.support.v4.startActivity

class ProfilFragment : Fragment() {

    private var sesi: SessionManager? = null



    private lateinit var viewModel: ProfilViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profil_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProfilViewModel::class.java)



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sesi = context?.let { SessionManager(it) }

        profileName.text = sesi?.nama
        profileName2.text = getString(R.string.name)+ sesi?.nama
        profileEmail.text = getString(R.string.Email) +  sesi?.email

        profileSignOut.setOnClickListener {
            sesi?.logout()

            startActivity<loginActivity>()
            activity?.finish()
        }

    }

}
