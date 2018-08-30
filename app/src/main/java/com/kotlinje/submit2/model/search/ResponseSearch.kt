package com.kotlinje.submit2.model.search


import com.google.gson.annotations.SerializedName


data class ResponseSearch(

	@field:SerializedName("event")
	val event: List<EventItem?>? = null
)