package com.kotlinje.submit2.presenter

import android.util.Log
import com.kotlinje.submit2.model.event.ResponseTeam
import com.kotlinje.submit2.model.liga.ResponseLiga
import com.kotlinje.submit2.model.liga.TeamsItem
import com.kotlinje.submit2.model.search.EventItem
import com.kotlinje.submit2.model.search.ResponseSearch
import com.kotlinje.submit2.model.search.ResponseSearchTeam
import com.kotlinje.submit2.network.repository.*
import com.kotlinje.submit2.view.SearchMatchView
import com.kotlinje.submit2.view.SearchTeamView
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

/**
 * Created by User on 15/05/2018.
 */
// add coruntines anko
//submission 4
class PresenterSearchTeam
(private val view  : SearchTeamView, private val matchRepository: MatchRepository)
{


    fun getTeamsSearch(id: String) {
        view.showLoadingProgress()
        matchRepository.getSearchTeams(id, object : SearchTeamRepositoryCallback<ResponseSearchTeam?> {
            override fun onDataLoaded(data: ResponseSearchTeam?) {
                view.onDataLoaded(data)

                async(UI){

                    val dataMatch = bg { data?.teams }
                    Log.e("data","coba teams : "+dataMatch)
                    //view.showEventList(dataMatch.await())
                    view.hideLoadingProgress()
                    view.showEventList(dataMatch.await() as List<com.kotlinje.submit2.model.search.TeamsItem>?)
                }

            }

            override fun onDataError() {
                view.onDataError()
                view.hideLoadingProgress()
            }
        })
        view.hideLoadingProgress()
    }


}