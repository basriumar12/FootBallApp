package com.kotlinje.submit2.model.liga

import com.google.gson.annotations.SerializedName

data class ResponseLiga(

	@field:SerializedName("teams")
	val teams: List<TeamsItem?>? = null
)