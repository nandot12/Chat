package com.nandohusni.baggit.repo

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.nandohusni.baggit.service.ApiService
import com.nandohusni.baggit.ui.addLokasi.model.Response
import com.nandohusni.baggit.ui.addLokasi.model.ResultAddLocations
import com.nandohusni.baggit.ui.locations.model.ResponseItem
import com.nandohusni.baggit.ui.locations.model.ResultLocation
import com.nandohusni.baggit.ui.login.model.Data
import com.nandohusni.baggit.ui.login.model.ResponseSignIn
import com.nandohusni.baggit.ui.signup.model.ResponseSignUp
import com.nandohusni.baggit.utils.Constans
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class BaggitRepository {


    private var service: ApiService? = null
    private var projectRepository: BaggitRepository? = null

    init {


        val log2 = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(log2).build()


        val retrofit = Retrofit.Builder()
            .baseUrl(Constans.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()

        service = retrofit.create(ApiService::class.java)
    }


    @SuppressLint("CheckResult")
    fun addLocation(iduser: String, lat: Double, lon: Double, desk: String): LiveData<Response> {

        val data = MutableLiveData<Response>()
        service?.addLocation(lon, lat, desk, iduser)?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({

                    t: ResultAddLocations? ->
                data.value = t?.response
            }, {

                    t: Throwable? ->
                data.value = null
            })

        return data
    }

    @SuppressLint("CheckResult")
    fun signUo(
        email: String,
        password: String,
        name: String,
        is_admin: String
    ): LiveData<com.nandohusni.baggit.ui.signup.model.Response> {

        val data = MutableLiveData<com.nandohusni.baggit.ui.signup.model.Response>()
        service?.singUp(name, password, is_admin, email)?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())?.subscribe(
                { t: ResponseSignUp? ->
                    data.value = t?.response
                }, {

                        t ->
                    data.value = null
                })



        return data
    }


    @SuppressLint("CheckResult")
    fun locationUser(iduser: String): LiveData<List<ResponseItem>> {

        val data = MutableLiveData<List<ResponseItem>>()
        service?.locationUser(iduser)?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.subscribe({

                    t: ResultLocation? ->
                if (t != null) {
                    data.value = t.response as List<ResponseItem>?
                }
            }, { t: Throwable? ->
                data.value = null
            })

        return data


    }

    @SuppressLint("CheckResult")
    fun locationAll(token: String): LiveData<List<ResponseItem>> {

        val data = MutableLiveData<List<ResponseItem>>()

        service?.locationAll()?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                { t: ResultLocation? ->

                    data.value = t?.response as List<ResponseItem>?
                }, {

                        t: Throwable? ->
                    data.value = null
                })

        return data
    }

    @SuppressLint("CheckResult")
    fun signIn(email: String, password: String): LiveData<Data> {

        val data = MutableLiveData<Data>()

        this.service?.signIn(email, password)?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({ t: ResponseSignIn? ->

                data.value = t?.data
            },
                { t ->
                    data.value = null
                })



        return data
    }

    @Synchronized
    fun getInstance(): BaggitRepository {
        if (projectRepository == null) {
            if (projectRepository == null) {
                projectRepository = com.nandohusni.baggit.repo.BaggitRepository()
            }
        }
        return projectRepository as BaggitRepository
    }


}