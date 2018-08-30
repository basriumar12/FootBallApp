package com.kotlinje.submit2.presenter

import com.kotlinje.submit2.model.detail_player.ResponseDetailPlayer
import com.kotlinje.submit2.network.repository.DetailRepositoryCallback
import com.kotlinje.submit2.network.repository.DetailRepositoryPlayerDetail
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
(private val view: DetailPlayerView, private val detailRepository: DetailRepositoryPlayerDetail)
{


    fun getDetailPlayer(idTeam: String) {
        view.showLoadingProgress()
        detailRepository.getDetailPlayer(idTeam, object : DetailRepositoryCallback<ResponseDetailPlayer?> {
            override fun onDataLoaded(data: ResponseDetailPlayer?) {
                view.hideLoadingProgress()
                async(UI){

                  val dataDetailPlayer = bg { data?.players?.get(0) }
                    println("data presenterdetail player :" +dataDetailPlayer)
                    view.showDetailPlayer(dataDetailPlayer.await()!!)
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