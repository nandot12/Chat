package com.nandohusni.baggit.ui.youtube

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.nandohusni.baggit.repo.YoutubeRepository
import com.nandohusni.baggit.ui.youtube.model.ItemsItem

class YoutubeViewModel(mBusRepository: Application?) : ViewModel() {


    // private val data : List<Response>? = null
    var repo: Application? = null


    init {

        repo = mBusRepository
    }


    fun youtube():LiveData<List<ItemsItem>>{

        return YoutubeRepository().getInstance().getYoutube()
    }


    class Factory(val application: Application) : ViewModelProvider.Factory {

        private var applications: Application? = null


        init {

            applications = application
        }


        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            return YoutubeViewModel(applications) as T
        }
    }
}
