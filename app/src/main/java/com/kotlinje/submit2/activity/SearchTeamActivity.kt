package com.kotlinje.submit2.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.kotlinje.submit2.R
import com.kotlinje.submit2.adapter.SearchTeamsAdapter
import com.kotlinje.submit2.model.search.ResponseSearchTeam
import com.kotlinje.submit2.model.search.TeamsItem
import com.kotlinje.submit2.network.repository.MatchRepository
import com.kotlinje.submit2.presenter.PresenterSearchTeam
import com.kotlinje.submit2.utility.invisible
import com.kotlinje.submit2.utility.visible
import com.kotlinje.submit2.view.SearchTeamView
import kotlinx.android.synthetic.main.activity_search_match.*
import kotlinx.android.synthetic.main.content_search.*
import org.jetbrains.anko.startActivity

class SearchTeamViewActivity : AppCompatActivity(), SearchTeamView {
    override fun onDataLoaded(data: ResponseSearchTeam?) {

    }

    override fun onDataError() {

    }

    override fun showLoadingProgress() {
    load?.visible()
    }

    override fun hideLoadingProgress() {
      load?.invisible()
    }

    override fun showEventList(data: List<TeamsItem>?) {
        teams.clear()
        if (data != null) {
            teams.addAll(data)

            Log.d("tag", "data teams :" + data)

        }
        adapterList?.notifyDataSetChanged()
    }


    var load : ProgressBar? = null

    var present : PresenterSearchTeam? =null
    //var adapterList : AdapterListData? = null
    var adapterList : SearchTeamsAdapter? = null
  //  var liga : MutableList<EventLiga> = mutableListOf()
    var teams : MutableList<TeamsItem> = mutableListOf()
    var hasil : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        rv_list_match.layoutManager = LinearLayoutManager(this@SearchTeamViewActivity)

        present = PresenterSearchTeam(this, MatchRepository())


        search_match.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                progressBarId.visibility = View.VISIBLE
                hasil = query!!

                Log.e("query","search : " +query)
                if (query.length > 4 ) {
                    //present?.getMatchLast(hasil!!)
                    present?.getTeamsSearch(hasil!!)

                    progressBarId.visibility = View.INVISIBLE
                    adapterList = SearchTeamsAdapter(teams){
                      startActivity<DetailTeamActivity>("idEvent" to it.idTeam)
                    }
                    rv_list_match.adapter = adapterList
                }
                return true
            //   return false
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onQueryTextChange(newText: String?): Boolean {


                return false
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })


    }
}
