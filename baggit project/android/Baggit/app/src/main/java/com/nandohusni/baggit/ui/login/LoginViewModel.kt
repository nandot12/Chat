package com.nandohusni.baggit.ui.login

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.nandohusni.baggit.repo.BaggitRepository
import com.nandohusni.baggit.ui.login.model.Data
import com.nandohusni.baggit.ui.signup.SignUpViewModel
import com.nandohusni.baggit.ui.signup.model.Response

class LoginViewModel(mBusRepository: Application?) : ViewModel() {


    // private val data : List<Response>? = null
    var repo: Application? = null


    init {

        repo = mBusRepository
    }

    fun sigIn(email: String, password: String): LiveData<Data> {

        return BaggitRepository().getInstance().signIn(email, password)


    }


    class Factory(val application: Application) : ViewModelProvider.Factory {

        private var applications: Application? = null


        init {

            applications = application
        }


        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            return LoginViewModel(applications) as T
        }
    }

}
