package com.kotlinje.submit2.model.event

import com.google.gson.annotations.SerializedName

/**
 * Created by User on 15/05/2018.
 */
// add coruntines anko
//submission 4
class ModelTeam {
    @SerializedName("teams")
    var teams: List<ModelTeamItem>? = null
}