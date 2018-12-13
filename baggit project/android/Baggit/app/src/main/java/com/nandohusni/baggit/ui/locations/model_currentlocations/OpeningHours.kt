package com.nandohusni.baggit.ui.locations.model_currentlocations

import com.google.gson.annotations.SerializedName

data class OpeningHours(

	@field:SerializedName("open_now")
	val openNow: Boolean? = null
)