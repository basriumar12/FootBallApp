package com.kotlinje.submit2.model

import com.google.gson.annotations.SerializedName

/**
 * Created by User on 15/05/2018.
 */
// add coruntines anko
//submission 4
class ResponseTeam {
    @SerializedName("events")
    var events: List<EventLiga>? = null
}