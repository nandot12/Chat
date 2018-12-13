package com.nandohusni.baggit.service

import android.support.annotation.DimenRes
import com.nandohusni.baggit.ui.addLokasi.model.ResultAddLocations
import com.nandohusni.baggit.ui.locations.model.ResultLocation
import com.nandohusni.baggit.ui.locations.model_currentlocations.ResultCurrentLocations
import com.nandohusni.baggit.ui.login.model.ResponseSignIn
import com.nandohusni.baggit.ui.signup.model.ResponseSignUp
import com.nandohusni.baggit.ui.youtube.model.ResponseYoutube
import com.nandohusni.baggit.utils.Constans
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

interface ApiService {


    @GET(Constans.coordinate)
    fun resultsearch(
        @Query("location") locations : String,
        @Query("rankby")rankby : String,
        @Query("types")type : String,
        @Query("sensor")sensor : Boolean,
        @Query("key") key : String
    ):Flowable<ResultCurrentLocations>

    @GET(Constans.spesifikUser)
    fun locationUser(@Query("id_user") id_user: String): Flowable<ResultLocation>

    @FormUrlEncoded
    @POST(Constans.addlocation)
    fun addLocation(
        @Field("long") long: Double,
        @Field("lat") lat: Double,
        @Field("description") desk: String,
        @Field("id_user") iduser: String
    ): Observable<ResultAddLocations>


    @GET(Constans.location)
    fun locationAll(): Flowable<ResultLocation>

    @GET(Constans.youtubeParams)
    fun youtubeGet(): Flowable<ResponseYoutube>

    @FormUrlEncoded
    @POST(Constans.signUp)
    fun singUp(
        @Field(Constans.NAME) name: String,
        @Field(Constans.PASSWORD) password: String,
        @Field(Constans.IS_ADMIN) is_admin: String,
        @Field(Constans.EMAIL) email: String
    ): Observable<ResponseSignUp>


    @FormUrlEncoded
    @POST(Constans.singIn)
    fun signIn(
        @Field(Constans.EMAIL) email: String,
        @Field(Constans.PASSWORD) password: String
    ): Single<ResponseSignIn>


}