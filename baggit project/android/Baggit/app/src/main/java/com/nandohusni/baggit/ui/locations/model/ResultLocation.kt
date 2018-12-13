package com.nandohusni.baggit.ui.locations.model

import com.google.gson.annotations.SerializedName

data class ResultLocation(

	@field:SerializedName("response")
	val response: List<ResponseItem?>? = null
)