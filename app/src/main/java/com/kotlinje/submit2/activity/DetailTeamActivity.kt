package com.kotlinje.submit2.activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.kotlinje.submit2.R
import com.kotlinje.submit2.adapter.TabAdapterTeam
import com.kotlinje.submit2.model.event.ModelFavoriteTeam
import com.kotlinje.submit2.model.event.ModelFavoriteTeam.Companion.TEAM_ID
import com.kotlinje.submit2.model.team.Team
import com.kotlinje.submit2.model.team.TeamResponse
import com.kotlinje.submit2.network.repository.DetailRepositoryTeamPlayer
import com.kotlinje.submit2.presenter.PresenterDetailTeam
import com.kotlinje.submit2.utility.database
import com.kotlinje.submit2.view.DetailTeamView
import kotlinx.android.synthetic.main.activity_detail_team.*
import kotlinx.android.synthetic.main.content_detail_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class DetailTeamActivity : AppCompatActivity(), DetailTeamView {
    override fun onDataLoaded(data: TeamResponse?) {
        showToastDetail("data response $data")
    }

    override fun showDetailTeam(team: Team?) {

        modelTeam = team
        val imgGambar = team?.teamBadge
        val thnTeam = team?.teamFormedYear
        val stadiumTeam = team?.teamStadium

        tv_thn_detail_team.text = thnTeam
        tv_stadium_detail_team.text = stadiumTeam
        Glide.with(this).load(imgGambar).into(img_detail_gambar)

    }


    override fun onDataError() {
        showToastDetail("Error Data")
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


    var load: ProgressBar? = null
    lateinit var idTeam: String
    var present: PresenterDetailTeam? = null
    var modelTeam: Team? = null
    private var menuItemDel: Menu? = null
    private var isTeamFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        setSupportActionBar(toolbar)
        //getintent
        idTeam = intent.getStringExtra("idTeam")
        modelTeam = Team()

        present = PresenterDetailTeam(this, DetailRepositoryTeamPlayer())
        present?.getDetailTeam(idTeam)

        id_tabs.addTab(id_tabs.newTab().setText("Overview"))
        id_tabs.addTab(id_tabs.newTab().setText("Player"))
        var adapter = TabAdapterTeam(supportFragmentManager, idTeam)
        id_pager.adapter = adapter
        id_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(id_tabs))
        id_tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }


            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    id_pager.currentItem = tab.position
                }
            }


        })

        cekFavor()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detai_activity, menu)
        menuItemDel = menu
        setFavorite(isTeamFavorite)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.favoriteteam -> {
                if (isTeamFavorite) {
                    removeFromFavorite()
                    isTeamFavorite = false
                } else {
                    addToFavorite()
                    isTeamFavorite = true
                }
                setFavorite(isTeamFavorite)
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }

    private fun setFavorite(isFav: Boolean) {
        if (isFav) {
            menuItemDel?.getItem(0)?.icon =
                    ContextCompat.getDrawable(this, R.drawable.ic_favorite_black_24dp)
        } else {
            menuItemDel?.getItem(0)?.icon =
                    ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_black_24dp)
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(ModelFavoriteTeam.TABLE_FAVORITE_TEAM,
                        ModelFavoriteTeam.TEAM_ID to modelTeam?.teamId,
                        ModelFavoriteTeam.TEAM_NAME to modelTeam?.teamName,
                        ModelFavoriteTeam.TEAM_IMAGE to modelTeam?.teamBadge
                )
            }
            showToastDetail("data berhasil disimpan")
        } catch (e: SQLiteConstraintException) {
            showToastDetail("data gagal di simpan :" + e.localizedMessage)

        }

    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(ModelFavoriteTeam.TABLE_FAVORITE_TEAM,
                        "($TEAM_ID = {id})", "id" to idTeam)
            }
            showToastDetail("hapus dari favorite ")

        } catch (e: SQLiteConstraintException) {
            showToastDetail("error Hapus :" + e.localizedMessage)
        }
    }

    private fun cekFavor() {
        try {

            database.use {
                println("dapat id di cekfavor :" + idTeam)

                val hasildata = select(ModelFavoriteTeam.TABLE_FAVORITE_TEAM)
                        .whereArgs("($TEAM_ID = {id})", "id" to idTeam)
                val teamFavorite = hasildata.parseList(classParser<ModelFavoriteTeam>())
                /*    if (!teamFavorite.isEmpty()){
                     isTeamFavorite = true
                    }
                      else{
                        isTeamFavorite=false
                    }
                */
                isTeamFavorite = !teamFavorite.isEmpty()
                setFavorite(isTeamFavorite)
            }

        } catch (e: SQLiteConstraintException) {
            showToastDetail("Error Cek Favorite : $e.localizedMessage")
        }
    }
}
