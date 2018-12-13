package com.nandohusni.baggit.ui.youtube.model

import com.google.gson.annotations.SerializedName

data class Id(

	@field:SerializedName("kind")
	val kind: String? = null,

	@field:SerializedName("videoId")
	val videoId: String? = null
)