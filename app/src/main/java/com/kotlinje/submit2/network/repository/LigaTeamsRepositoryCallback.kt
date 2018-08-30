package com.kotlinje.submit2.network.repository

import com.kotlinje.submit2.model.event.ModelTeam
import com.kotlinje.submit2.model.event.ResponseTeam
import com.kotlinje.submit2.model.liga.ResponseLiga
import com.kotlinje.submit2.model.search.ResponseSearch
import com.kotlinje.submit2.model.team.TeamResponse

/**
 * Created by User on 27/05/2018.
 */
interface TeamsRepositoryCallback <T> {

    fun onDataLoaded(data: ResponseLiga?)
    fun onDataError()
}