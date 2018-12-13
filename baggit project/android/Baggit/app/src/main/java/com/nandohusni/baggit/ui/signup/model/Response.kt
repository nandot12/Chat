package com.nandohusni.baggit.ui.signup.model

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("is_admin")
	val isAdmin: String? = null,

	@field:SerializedName("result")
	val result: String? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("resulttext")
	val resulttext: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("resultcode")
	val resultcode: Int? = null,

	@field:SerializedName("userId")
	val userId: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("token")
	val token: String? = null
)