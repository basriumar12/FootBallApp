package com.kotlinje.submit2.view

import com.kotlinje.submit2.model.detail_player.PlayersItemDetail
import com.kotlinje.submit2.model.detail_player.ResponseDetailPlayer
import com.kotlinje.submit2.model.player.PlayersItem
import com.kotlinje.submit2.model.player.ResponsePlayer
import com.kotlinje.submit2.network.repository.DetailRepositoryCallback

/**
 * Created by User on 15/05/2018.
 */
// add coruntines anko
//submission 4
interface DetailPlayerView : DetailRepositoryCallback<ResponseDetailPlayer> {
    fun showLoadingProgress()
    fun hideLoadingProgress()
    fun showToastDetail(message: String?)
    fun showDetailPlayer(team:PlayersItemDetail)
}