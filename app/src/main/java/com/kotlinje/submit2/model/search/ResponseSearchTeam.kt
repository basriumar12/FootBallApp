package com.kotlinje.submit2.model.search


import com.google.gson.annotations.SerializedName


data class ResponseSearchTeam(

	@field:SerializedName("teams")
	val teams: List<TeamsItem?>? = null
)