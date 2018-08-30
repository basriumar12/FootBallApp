package com.kotlinje.submit2.network.repository

import com.kotlinje.submit2.model.event.ResponseTeam

/**
 * Created by User on 27/05/2018.
 */
interface MatchRepositoryCallback <T> {

    fun onDataLoaded(data: ResponseTeam?)
    fun onDataError()
}