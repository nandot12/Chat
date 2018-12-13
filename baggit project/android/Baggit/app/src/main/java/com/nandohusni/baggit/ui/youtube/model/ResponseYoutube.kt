package com.nandohusni.baggit.ui.youtube.model

import com.google.gson.annotations.SerializedName

data class ResponseYoutube(

	@field:SerializedName("regionCode")
	val regionCode: String? = null,

	@field:SerializedName("kind")
	val kind: String? = null,

	@field:SerializedName("nextPageToken")
	val nextPageToken: String? = null,

	@field:SerializedName("pageInfo")
	val pageInfo: PageInfo? = null,

	@field:SerializedName("etag")
	val etag: String? = null,

	@field:SerializedName("items")
	val items: List<ItemsItem?>? = null
)