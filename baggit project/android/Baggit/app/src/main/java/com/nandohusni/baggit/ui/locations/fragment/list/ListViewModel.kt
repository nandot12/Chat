package com.nandohusni.baggit.ui.locations.fragment.list

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider
import com.nandohusni.baggit.repo.BaggitRepository
import com.nandohusni.baggit.ui.locations.model.ResponseItem


class ListViewModel(mBusRepository : Application?) : ViewModel() {


    // private val data : List<Response>? = null
    var repo: Application? = null


    init {

        repo = mBusRepository
    }


    fun userLocation(user : String): LiveData<List<ResponseItem>> {
        return BaggitRepository().getInstance().locationUser(user)
    }


    class Factory(val application: Application) : ViewModelProvider.Factory {

        private var applications: Application? = null


        init {

            applications = application
        }


        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            return ListViewModel(applications) as T
        }
    }
}
