package com.nandohusni.baggit.ui.locations.model_currentlocations

import com.google.gson.annotations.SerializedName

data class ResultCurrentLocations(

	@field:SerializedName("html_attributions")
	val htmlAttributions: List<Any?>? = null,

	@field:SerializedName("results")
	val results: List<ResultsItem?>? = null,

	@field:SerializedName("status")
	val status: String? = null
)