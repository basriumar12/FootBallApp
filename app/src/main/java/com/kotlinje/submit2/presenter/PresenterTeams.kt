package com.kotlinje.submit2.presenter

import android.util.Log
import com.kotlinje.submit2.model.liga.ResponseLiga
import com.kotlinje.submit2.model.liga.TeamsItem
import com.kotlinje.submit2.network.repository.MatchRepository
import com.kotlinje.submit2.network.repository.LigaTeamsRepositoryCallback
import com.kotlinje.submit2.view.LigaTeamsSearchVIiew
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

/**
 * Created by User on 15/05/2018.
 */
// add coruntines anko
//submission 4
class PresenterTeams
(private val view  : LigaTeamsSearchVIiew, private val matchRepository: MatchRepository)
{


    fun getLigaTeamsSearch(id: String) {
        view.showLoadingProgress()
        matchRepository.getSearchLigaTeams(id, object : LigaTeamsRepositoryCallback<ResponseLiga?> {
            override fun onDataLoaded(data: ResponseLiga?) {
              view.onDataLoaded(data)

                async(UI){

                    val dataMatch = bg { data?.teams }
                    Log.e("data","coba teams : "+dataMatch)
                    //view.showEventList(dataMatch.await())
                    view.hideLoadingProgress()
                    view.showEventList(dataMatch.await() as List<TeamsItem>?)
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