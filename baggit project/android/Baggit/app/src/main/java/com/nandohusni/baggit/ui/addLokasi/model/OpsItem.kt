package com.nandohusni.baggit.ui.addLokasi.model

import com.google.gson.annotations.SerializedName

data class OpsItem(

	@field:SerializedName("area")
	val area: Area? = null,

	@field:SerializedName("timeStamp")
	val timeStamp: String? = null,

	@field:SerializedName("id_increment")
	val idIncrement: Int? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id_user")
	val idUser: String? = null,

	@field:SerializedName("_id")
	val id: String? = null
)