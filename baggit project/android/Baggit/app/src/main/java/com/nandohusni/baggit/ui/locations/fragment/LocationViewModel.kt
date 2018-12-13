package com.nandohusni.baggit.ui.locations.fragment

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.nandohusni.baggit.repo.BaggitRepository
import com.nandohusni.baggit.repo.GoogleSearchRepository
import com.nandohusni.baggit.ui.locations.model.ResponseItem
import com.nandohusni.baggit.ui.locations.model_currentlocations.ResultsItem

class LocationViewModel(mBusRepository : Application?) : ViewModel() {


    var repo: Application? = null


    init {

        repo = mBusRepository
    }

    fun allLocations(token : String): LiveData<List<ResponseItem>> {

        return BaggitRepository().getInstance().locationAll(token)


    }

    fun searchLocations(locations : String,rankBy : String,type : String,sensor : Boolean,key :String):LiveData<List<ResultsItem>>{

        return GoogleSearchRepository().getInstance().getLocations(locations,rankBy,type,sensor,key)
    }


    fun userLocation(user : String):LiveData<List<ResponseItem>>{
        return BaggitRepository().getInstance().locationUser(user)
    }


    class Factory(val application: Application) : ViewModelProvider.Factory {

        private var applications: Application? = null


        init {

            applications = application
        }


        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            return LocationViewModel(applications) as T
        }
    }
}
