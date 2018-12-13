package com.nandohusni.baggit.ui.youtube.model

import com.google.gson.annotations.SerializedName

data class PageInfo(

	@field:SerializedName("totalResults")
	val totalResults: Int? = null,

	@field:SerializedName("resultsPerPage")
	val resultsPerPage: Int? = null
)