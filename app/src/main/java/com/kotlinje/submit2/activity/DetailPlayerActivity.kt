package com.kotlinje.submit2.activity

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.kotlinje.submit2.R
import com.kotlinje.submit2.model.detail_player.PlayersItemDetail
import com.kotlinje.submit2.model.detail_player.ResponseDetailPlayer
import com.kotlinje.submit2.network.repository.DetailRepositoryPlayerDetail
import com.kotlinje.submit2.presenter.PresenterDetailPlayer
import com.kotlinje.submit2.view.DetailPlayerView
import kotlinx.android.synthetic.main.activity_detail_player.*
import kotlinx.android.synthetic.main.activity_detail_team.*
import kotlinx.android.synthetic.main.content_detail_player.*
import org.jetbrains.anko.toast

class DetailPlayerActivity : AppCompatActivity(), DetailPlayerView {
    override fun onDataLoaded(data: ResponseDetailPlayer?) {
        println("dapat data response : $data")
    }

    override fun onDataError() {
        println("error data")
    }

    override fun showLoadingProgress() {
        load?.visibility = View.VISIBLE

    }

    override fun hideLoadingProgress() {
        load?.visibility = View.INVISIBLE

    }

    override fun showToastDetail(message: String?) {
        toast(message.toString())
    }

    override fun showDetailPlayer(team: PlayersItemDetail) {

        playerItem = team
        val getImgPlayer = team?.strThumb
        val tvBeratPlayer = team?.strWeight
        val tvTinggiPlayer = team?.strHeight
        val tvDesk = team?.strDescriptionEN
        tv_berat_player.text = tvBeratPlayer
        tv_tinggi_player.text = tvTinggiPlayer
        tv_desc_player.text = tvDesk
        Glide.with(this).load(getImgPlayer).into(img_player_detail)
    }

    var load: ProgressBar? = null
    var present: PresenterDetailPlayer? = null
    var idPlayer: String? = null
    var playerItem: PlayersItemDetail? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)

        idPlayer = intent.getStringExtra("idPlayer")
        playerItem = PlayersItemDetail()

        present = PresenterDetailPlayer(this, DetailRepositoryPlayerDetail())
        present?.getDetailPlayer(idPlayer!!)

    }
}
