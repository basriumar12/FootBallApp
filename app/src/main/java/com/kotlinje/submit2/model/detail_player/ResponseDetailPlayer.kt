package com.kotlinje.submit2.model.detail_player

import com.google.gson.annotations.SerializedName


data class ResponseDetailPlayer(

	@field:SerializedName("players")
	val players: List<PlayersItemDetail?>? = null
)