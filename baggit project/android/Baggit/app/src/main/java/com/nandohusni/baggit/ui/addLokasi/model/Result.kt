package com.nandohusni.baggit.ui.addLokasi.model

import com.google.gson.annotations.SerializedName

data class Result(

	@field:SerializedName("ok")
	val ok: Int? = null,

	@field:SerializedName("n")
	val N: Int? = null
)