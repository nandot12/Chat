package com.nandohusni.baggit.ui.signup

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.nandohusni.baggit.repo.BaggitRepository
import com.nandohusni.baggit.ui.signup.model.Response

class SignUpViewModel(mBusRepository: Application?) : ViewModel() {

   // private val data : List<Response>? = null
    var repo: Application? = null


    init {

        repo = mBusRepository
    }

    fun loadSignUp(name: String, email: String, password: String, is_admin: String): LiveData<Response> {

        return BaggitRepository().getInstance().signUo(email,password,name,is_admin)




    }


    class SignUpViewModelFactory(val application: Application) :  ViewModelProvider.Factory {

        private var applications: Application? = null


        init {

            applications = application
        }


        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            return SignUpViewModel(applications) as T
        }
    }


}
