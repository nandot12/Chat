package com.nandohusni.baggit.repo

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.nandohusni.baggit.service.ApiService
import com.nandohusni.baggit.ui.youtube.model.ItemsItem
import com.nandohusni.baggit.utils.Constans
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class YoutubeRepository {

    var api: ApiService? = null
    var projectRepository: YoutubeRepository? = null


    init {


        val log2 = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(log2).build()


        val retrofit = Retrofit.Builder()
            .baseUrl(Constans.BaseUrlYoutube)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()

        api = retrofit.create(ApiService::class.java)
    }


    @SuppressLint("CheckResult")
    fun getYoutube(): LiveData<List<ItemsItem>> {
        val data = MutableLiveData<List<ItemsItem>>()

        api?.youtubeGet()?.observeOn(AndroidSchedulers.mainThread())?.subscribeOn(Schedulers.io())
            ?.subscribe({

                    t ->
                data.value = t.items as List<ItemsItem>?

            }, {})

        return data
    }


    @Synchronized
    fun getInstance(): YoutubeRepository {
        if (projectRepository == null) {
            if (projectRepository == null) {
                projectRepository = com.nandohusni.baggit.repo.YoutubeRepository()
            }
        }
        return projectRepository as YoutubeRepository


    }
}