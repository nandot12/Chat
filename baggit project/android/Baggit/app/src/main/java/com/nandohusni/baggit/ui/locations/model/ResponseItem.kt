package com.nandohusni.baggit.ui.locations.model

import com.google.gson.annotations.SerializedName

data class ResponseItem(

	@field:SerializedName("area")
	val area: Area? = null,

	@field:SerializedName("timeStamp")
	val timeStamp: String? = null,

	@field:SerializedName("id_increment")
	val idIncrement: Int? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("id_user")
	val idUser: String? = null,

	@field:SerializedName("long")
	val jsonMemberLong: Double? = null,

	@field:SerializedName("lat")
	val lat: Double? = null
)