package com.nandohusni.baggit.ui.youtube.model

import com.google.gson.annotations.SerializedName

data class Medium(

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("height")
	val height: Int? = null
)