package com.kotlinje.submit2.presenter

import com.kotlinje.submit2.model.player.PlayersItem
import com.kotlinje.submit2.model.player.ResponsePlayer
import com.kotlinje.submit2.network.repository.DetailRepositoryCallback
import com.kotlinje.submit2.network.repository.DetailRepositoryPlayer
import com.kotlinje.submit2.view.DetailListPlayerView
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

/**
 * Created by User on 15/05/2018.
 */

// add coruntines anko
//submission 4
class PresenterDetailPlayer
(private val view: DetailListPlayerView, private val detailRepository: DetailRepositoryPlayer)
{


    fun getDetailListPlayer(idTeam: String) {
        view.showLoadingProgress()
        detailRepository.getDetailListPlayer(idTeam, object : DetailRepositoryCallback<ResponsePlayer?> {
            override fun onDataLoaded(data: ResponsePlayer?) {
                view.hideLoadingProgress()
                async(UI){

                  val dataPlayer = bg { data?.player }
                    println("data presenterdetail team :" +dataPlayer)
                    view.showDetailListPlayer(dataPlayer.await() as List<PlayersItem>?)
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