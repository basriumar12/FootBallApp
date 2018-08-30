package com.kotlinje.submit2.network.repository

import com.kotlinje.submit2.model.event.ResponseTeam
import com.kotlinje.submit2.model.search.ResponseSearch

/**
 * Created by User on 27/05/2018.
 */
interface SearchRepositoryCallback <T> {

    fun onDataLoaded(data: ResponseSearch?)
    fun onDataError()
}