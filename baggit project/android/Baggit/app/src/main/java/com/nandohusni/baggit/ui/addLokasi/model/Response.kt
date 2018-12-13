package com.nandohusni.baggit.ui.addLokasi.model

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("result")
	val result: Result? = null,

	@field:SerializedName("ops")
	val ops: List<OpsItem?>? = null,

	@field:SerializedName("insertedIds")
	val insertedIds: InsertedIds? = null,

	@field:SerializedName("insertedCount")
	val insertedCount: Int? = null
)