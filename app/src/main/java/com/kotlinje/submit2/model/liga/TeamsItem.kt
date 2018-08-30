package com.kotlinje.submit2.model.liga

import com.google.gson.annotations.SerializedName

data class TeamsItem(



	@field:SerializedName("idTeam")
	val idTeam: String? = null,
	@SerializedName("strTeam")
	val strTeam: String? = null,
	@SerializedName("strTeamBadge")
	val strTeamBadge: String? = null


)