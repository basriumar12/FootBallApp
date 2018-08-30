package com.kotlinje.submit2.network.repository

import com.kotlinje.submit2.model.event.ResponseTeam
import com.kotlinje.submit2.model.search.ResponseSearch
import com.kotlinje.submit2.model.search.ResponseSearchTeam

/**
 * Created by User on 27/05/2018.
 */
interface SearchTeamRepositoryCallback <T> {

    fun onDataLoaded(data: ResponseSearchTeam?)
    fun onDataError()
}