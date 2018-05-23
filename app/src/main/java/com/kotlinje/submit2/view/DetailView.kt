package com.kotlinje.submit2.view

import com.kotlinje.submit2.model.EventLiga
import com.kotlinje.submit2.model.ModelTeamItem

/**
 * Created by User on 15/05/2018.
 */
// add coruntines anko
//submission 4
interface DetailView {
    fun showLoadingProgress()
    fun hideLoadingProgress()
    fun showToastDetail(message: String?)
    fun showEventLiga(event: EventLiga?)
    fun showHomeTeamImg(team: ModelTeamItem?)
    fun showAwayTeamImg(team: ModelTeamItem?)
}