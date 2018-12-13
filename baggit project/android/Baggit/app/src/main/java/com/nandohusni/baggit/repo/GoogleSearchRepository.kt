package com.nandohusni.baggit.repo

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.nandohusni.baggit.service.ApiService
import com.nandohusni.baggit.ui.locations.model_currentlocations.ResultCurrentLocations
import com.nandohusni.baggit.ui.locations.model_currentlocations.ResultsItem
import com.nandohusni.baggit.ui.youtube.model.ItemsItem
import com.nandohusni.baggit.utils.Constans
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GoogleSearchRepository {

    var api: ApiService? = null
    var projectRepository: GoogleSearchRepository? = null


    init {


        val log2 = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(log2).build()


        val retrofit = Retrofit.Builder()
            .baseUrl(Constans.baseUrlgooglesearch)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()

        api = retrofit.create(ApiService::class.java)
    }


    @SuppressLint("CheckResult")
    fun getLocations(locations : String,rankBy : String,type : String,sensor : Boolean,key :String): LiveData<List<ResultsItem>> {
        val data = MutableLiveData<List<ResultsItem>>()
        api?.
            resultsearch(locations,rankBy,type,sensor,key)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                t: ResultCurrentLocations? ->  data.value = t?.results as List<ResultsItem>?
            },{
                t: Throwable? ->

                data.value = null
            })




        return data
    }


    @Synchronized
    fun getInstance(): GoogleSearchRepository {
        if (projectRepository == null) {
            if (projectRepository == null) {
                projectRepository = com.nandohusni.baggit.repo.GoogleSearchRepository()
            }
        }
        return projectRepository as GoogleSearchRepository


    }
}