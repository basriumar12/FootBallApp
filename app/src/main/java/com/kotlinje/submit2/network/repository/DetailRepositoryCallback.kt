package com.kotlinje.submit2.network.repository

/**
 * Created by User on 27/05/2018.
 */
interface DetailRepositoryCallback <T> {

    fun onDataLoaded(data: T?)
    fun onDataError()
}