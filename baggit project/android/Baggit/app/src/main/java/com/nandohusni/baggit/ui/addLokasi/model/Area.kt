package com.nandohusni.baggit.ui.addLokasi.model

import com.google.gson.annotations.SerializedName

data class Area(

    @field:SerializedName("coordinates")
    val coordinates: List<Double?>? = null,

    @field:SerializedName("type")
    val type: String? = null
)