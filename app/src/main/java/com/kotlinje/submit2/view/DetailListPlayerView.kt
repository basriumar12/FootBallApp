package com.kotlinje.submit2.view

import com.kotlinje.submit2.model.player.PlayersItem
import com.kotlinje.submit2.model.player.ResponsePlayer
import com.kotlinje.submit2.network.repository.DetailRepositoryCallback

/**
 * Created by User on 15/05/2018.
 */
// add coruntines anko
//submission 4
interface DetailListPlayerView : DetailRepositoryCallback<ResponsePlayer> {
    fun showLoadingProgress()
    fun hideLoadingProgress()
    fun showToastDetail(message: String?)
    fun showDetailListPlayer(team:List <PlayersItem>?)
}