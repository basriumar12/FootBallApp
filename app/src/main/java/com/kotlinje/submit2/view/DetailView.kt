package com.kotlinje.submit2.view

import com.kotlinje.submit2.model.event.EventLiga
import com.kotlinje.submit2.model.event.ModelTeam
import com.kotlinje.submit2.model.event.ModelTeamItem
import com.kotlinje.submit2.network.repository.DetailRepositoryCallback

/**
 * Created by User on 15/05/2018.
 */
// add coruntines anko
//submission 4
interface DetailView : DetailRepositoryCallback<ModelTeam> {
    fun showLoadingProgress()
    fun hideLoadingProgress()
    fun showToastDetail(message: String?)
    fun showEventLiga(event: EventLiga?)
    fun showHomeTeamImg(team: ModelTeamItem?)
    fun showAwayTeamImg(team: ModelTeamItem?)
}