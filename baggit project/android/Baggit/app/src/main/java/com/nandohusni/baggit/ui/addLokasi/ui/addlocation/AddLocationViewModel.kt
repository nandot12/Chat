package com.nandohusni.baggit.ui.addLokasi.ui.addlocation

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.nandohusni.baggit.repo.BaggitRepository
import com.nandohusni.baggit.ui.addLokasi.model.Response
import com.nandohusni.baggit.ui.login.model.Data

class AddLocationViewModel(mBusRepository: Application?) : ViewModel() {
    // TODO: Implement the ViewModel

    // private val data : List<Response>? = null
    var repo: Application? = null


    init {

        repo = mBusRepository
    }


    fun addLocation(lat : Double,lon : Double,desk : String,iduser : String):LiveData<Response>{

        return BaggitRepository().getInstance().addLocation(iduser,lat,lon,desk)
    }

    class Factory(val application: Application) : ViewModelProvider.Factory {

        private var applications: Application? = null


        init {

            applications = application
        }


        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            return AddLocationViewModel(applications) as T
        }
    }

}
