package com.kotlinje.submit2.presenter

import com.kotlinje.submit2.model.event.ModelTeam
import com.kotlinje.submit2.model.team.TeamResponse
import com.kotlinje.submit2.network.repository.DetailRepositoryCallback
import com.kotlinje.submit2.network.repository.DetailRepositoryTeamPlayer
import com.kotlinje.submit2.view.DetailPlayerView
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

/**
 * Created by User on 15/05/2018.
 */

// add coruntines anko
//submission 4
class PresenterDetailPlayer
(private val view: DetailPlayerView, private val detailRepository: DetailRepositoryTeamPlayer)
{


    fun getDetailTeam(idTeam: String) {
        view.showLoadingProgress()
        detailRepository.getDetailTeam(idTeam, object : DetailRepositoryCallback<TeamResponse?> {
            override fun onDataLoaded(data: TeamResponse?) {
                view.hideLoadingProgress()
                async(UI){

                  val dataImgAway = bg { data?.teams?.get(0) }
                    println("data presenterdetail team :" +dataImgAway)
                    view.showDetailTeam(dataImgAway.await())
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