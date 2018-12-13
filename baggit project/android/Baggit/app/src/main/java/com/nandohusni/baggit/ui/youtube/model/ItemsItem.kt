package com.nandohusni.baggit.ui.youtube.model

import com.google.gson.annotations.SerializedName

data class ItemsItem(

	@field:SerializedName("snippet")
	val snippet: Snippet? = null,

	@field:SerializedName("kind")
	val kind: String? = null,

	@field:SerializedName("etag")
	val etag: String? = null,

	@field:SerializedName("id")
	val id: Id? = null
)