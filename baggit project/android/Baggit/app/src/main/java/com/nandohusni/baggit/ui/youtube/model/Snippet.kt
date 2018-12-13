package com.nandohusni.baggit.ui.youtube.model

import com.google.gson.annotations.SerializedName

data class Snippet(

	@field:SerializedName("publishedAt")
	val publishedAt: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("thumbnails")
	val thumbnails: Thumbnails? = null,

	@field:SerializedName("channelId")
	val channelId: String? = null,

	@field:SerializedName("channelTitle")
	val channelTitle: String? = null,

	@field:SerializedName("liveBroadcastContent")
	val liveBroadcastContent: String? = null
)