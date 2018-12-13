package com.nandohusni.baggit.ui.login.model

import com.google.gson.annotations.SerializedName

data class Data(

	@field:SerializedName("created")
	val created: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("ttl")
	val ttl: Int? = null,

	@field:SerializedName("userId")
	val userId: String? = null
)