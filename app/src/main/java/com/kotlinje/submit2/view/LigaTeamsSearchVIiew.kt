package com.kotlinje.submit2.view

import com.kotlinje.submit2.model.event.ModelTeam
import com.kotlinje.submit2.model.event.ResponseTeam
import com.kotlinje.submit2.model.liga.ResponseLiga
import com.kotlinje.submit2.model.liga.TeamsItem
import com.kotlinje.submit2.model.search.EventItem
import com.kotlinje.submit2.model.search.ResponseSearch
import com.kotlinje.submit2.model.team.Team
import com.kotlinje.submit2.model.team.TeamResponse
import com.kotlinje.submit2.network.repository.MatchRepositoryCallback
import com.kotlinje.submit2.network.repository.TeamsRepositoryCallback

/**
 * Created by User on 02/06/2018.
 */
interface TeamsSearchVIiew : TeamsRepositoryCallback<ResponseLiga> {

    fun showLoadingProgress()
    fun hideLoadingProgress()
    fun showEventList(data:List<TeamsItem>?)
}