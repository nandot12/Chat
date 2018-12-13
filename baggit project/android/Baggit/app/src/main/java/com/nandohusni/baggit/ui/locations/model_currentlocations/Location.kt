package com.nandohusni.baggit.ui.locations.model_currentlocations

import com.google.gson.annotations.SerializedName

data class Location(

	@field:SerializedName("lng")
	val lng: Double? = null,

	@field:SerializedName("lat")
	val lat: Double? = null
)