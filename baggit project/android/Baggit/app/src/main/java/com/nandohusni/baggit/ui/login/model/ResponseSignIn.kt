package com.nandohusni.baggit.ui.login.model

import com.google.gson.annotations.SerializedName

data class ResponseSignIn(

	@field:SerializedName("result")
	val result: String? = null,

	@field:SerializedName("data")
	val data: Data? = null
)