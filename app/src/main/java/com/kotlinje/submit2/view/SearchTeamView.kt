package com.kotlinje.submit2.view

import com.kotlinje.submit2.model.search.EventItem
import com.kotlinje.submit2.model.search.ResponseSearch
import com.kotlinje.submit2.model.search.ResponseSearchTeam
import com.kotlinje.submit2.model.search.TeamsItem
import com.kotlinje.submit2.network.repository.MatchRepositoryCallback
import com.kotlinje.submit2.network.repository.SearchTeamRepositoryCallback

/**
 * Created by User on 31/05/2018.
 */
interface SearchTeamView : SearchTeamRepositoryCallback<ResponseSearchTeam> {

    fun showLoadingProgress()
    fun hideLoadingProgress()
    fun showEventList(data:List<TeamsItem>?)
}