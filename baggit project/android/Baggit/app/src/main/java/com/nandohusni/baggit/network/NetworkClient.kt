package com.nandohusni.baggit.network

import com.nandohusni.baggit.service.ApiService
import com.nandohusni.baggit.utils.Constans
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory


object NetworkClient {


    val log2 = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val client = OkHttpClient.Builder()
        .addInterceptor(log2).build()


    var retrofit = Retrofit.Builder()
        .baseUrl(Constans.BaseUrlYoutube)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(client)
        .build()


    var service: ApiService = retrofit.create(ApiService::class.java!!)
}