package com.kotlinje.submit2.model.player


import com.google.gson.annotations.SerializedName


data class ResponsePlayer(

	@field:SerializedName("player")
	val player: List<PlayersItem?>? = null
)