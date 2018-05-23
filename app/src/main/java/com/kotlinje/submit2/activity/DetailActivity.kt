package com.kotlinje.submit2.activity

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.kotlinje.submit2.R
import com.kotlinje.submit2.model.EventLiga
import com.kotlinje.submit2.model.ModelFavorite
import com.kotlinje.submit2.model.ModelTeamItem
import com.kotlinje.submit2.presenter.PresenterDetail
import com.kotlinje.submit2.view.DetailView
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.toast
import com.kotlinje.submit2.utility.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.select
// add coruntines anko
//submission 4
//pada detail activity menampilkan 2 tim dan benderanya
//selain itu menampilkan menu item untuk menambahkan team favorite
class DetailActivity : AppCompatActivity(), DetailView {

    private fun addToFavorite(){


        try {
            database.use {
                insert(ModelFavorite.TABLE_FAVORITE,
                        ModelFavorite.EVENT_ID to eventLiga?.idEvent,
                        ModelFavorite.EVENT_DATE to eventLiga?.dateEvent,
                        ModelFavorite.HOME_TEAM to eventLiga?.strHomeTeam,
                        ModelFavorite.AWAY_TEAM to eventLiga?.strAwayTeam,
                        ModelFavorite.SCORE_HOME to eventLiga?.intHomeScore,
                        ModelFavorite.SCORE_AWAY to eventLiga?.intAwayScore
                )
            }

            showToastDetail("data berhasil disimpan")
        } catch (e: SQLiteConstraintException) {
            showToastDetail("Error Insert"+e.localizedMessage)

        }
    }
    // remove berfungsi untuk delete data
    private fun removeFromFavorite(){
        try {
            database.use {
             //delete data dari tabel favorite berdasarkan id yang di pilih
                delete(ModelFavorite.TABLE_FAVORITE,
                        "(EVENT_ID = {id})","id" to idEvent)
//                delete(EventFavorite.TABLE_FAVORITE,"(EVENT_ID = {id})","id" to )
            }
            showToastDetail("hapus dari favorite")
        } catch (e: SQLiteConstraintException) {
            showToastDetail("error hapus :"+e.localizedMessage)
//
        }
    }
    //
    private fun setFavorite(isFav:Boolean){
        if(isFav){

            menuItemDel?.getItem(0)?.icon =
                    ContextCompat.getDrawable(this,R.drawable.ic_favorite_black_24dp)
        }else{
            menuItemDel?.getItem(0)?.icon =
                    ContextCompat.getDrawable(this,R.drawable.ic_favorite_border_black_24dp)
        }
    }
     //mengecek keadaan favorit
    // ketika favorite nol data maka akan di tampilkan gambar border black
    // dan jika favorite ada data makan akan di tampilkan gambar pada menu item adalah black
    private fun cekFavor(){
        try {
            database.use {
                //query select id
                val hasilData = select(ModelFavorite.TABLE_FAVORITE)
                        .whereArgs("(EVENT_ID = {id})","id" to idEvent)
                val eventFavoriteTeam = hasilData.parseList(classParser<ModelFavorite>())


                if(!eventFavoriteTeam.isEmpty()){
                    isEvenFavor = true
                }else{
                    isEvenFavor = false
                }
                //set favorite match team
                setFavorite(isEvenFavor)
            }

        } catch (e: SQLiteConstraintException) {
            showToastDetail("Error Cek Favorit"+e.localizedMessage)
        }
    }

    override fun showHomeTeamImg(team: ModelTeamItem?) {
        var imgHome = team?.strTeamBadge
        Glide.with(this).load(imgHome).into(imgHomeTeam)

        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showAwayTeamImg(team: ModelTeamItem?) {
        var imgAway = team?.strTeamBadge
        Glide.with(this).load(imgAway).into(imgAwayTeam)
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoadingProgress() {
        load?.visibility = View.VISIBLE
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoadingProgress() {
        load?.visibility = View.INVISIBLE
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showToastDetail(message: String?) {
        toast(message.toString())
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //create method untuk muncul menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detai_activity, menu)
        menuItemDel = menu
        setFavorite(isEvenFavor)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            android.R.id.home -> {
                finish()
                true
            }
            R.id.favoriteteam ->{
                // kondisi pada menu item
                // remove / delete
                if (isEvenFavor){
                    removeFromFavorite()
                    isEvenFavor = false
                }else{
                    // add to favorite
                    addToFavorite()
                    isEvenFavor = true
                }

                //set favorite
                setFavorite(isEvenFavor)
                true
            }
            else ->super.onOptionsItemSelected(item)

        }
    }
    //show event liga
    override fun showEventLiga(event: EventLiga?) {
        eventLiga = event
        idHomeTeam = event?.idHomeTeam
        idAwayTeam = event?.idAwayTeam
        textScoreHomeTeam.text = event?.intHomeScore
        textScoreAwayTeam.text = event?.intAwayScore
        textGoalHomeTeam.text = event?.strHomeGoalDetails
        tvGoalAwayTeam.text = event?.strAwayGoalDetails
        present?.getHomeimg(idHomeTeam)
        present?.getAwayImg(idAwayTeam)

        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //variabel
    private var  isEvenFavor: Boolean = false
    var load: ProgressBar? = null
    var present: PresenterDetail? = null
    var idHomeTeam: String? = null
    var idAwayTeam: String? = null
    var eventLiga: EventLiga? = null
    lateinit var imgHomeTeam: ImageView
    lateinit var imgAwayTeam: ImageView
    lateinit var textScoreHomeTeam: TextView
    lateinit var textScoreAwayTeam: TextView
    lateinit var textGoalHomeTeam: TextView
    lateinit var tvGoalAwayTeam: TextView
    lateinit var idEvent: String
    private var menuItemDel: Menu? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        //get intent
        idEvent = intent.getStringExtra("idEvent")
        present = PresenterDetail(this)
        present?.getLastLiga(idEvent)
        imgHomeTeam = img_home_team
        imgAwayTeam = img_away_team
        textScoreHomeTeam = text_score_home_team
        textScoreAwayTeam = text_score_away_team
        textGoalHomeTeam = text_goal_home_team
        tvGoalAwayTeam = text_goal_away_team

        // pada oncrete method dicek dulu favoritenya,
        // sehingga pada menu item icon akan terganti
        cekFavor();




    }
}
