package com.kotlinje.submit2.view

import com.kotlinje.submit2.model.liga.ResponseLiga
import com.kotlinje.submit2.model.liga.TeamsItem
import com.kotlinje.submit2.network.repository.LigaTeamsRepositoryCallback

/**
 * Created by User on 02/06/2018.
 */
interface LigaTeamsSearchVIiew : LigaTeamsRepositoryCallback<ResponseLiga> {

    fun showLoadingProgress()
    fun hideLoadingProgress()
    fun showEventList(data:List<TeamsItem>?)
}