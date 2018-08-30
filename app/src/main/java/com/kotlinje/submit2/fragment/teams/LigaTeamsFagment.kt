package com.kotlinje.submit2.fragment

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.kotlinje.submit2.R
import com.kotlinje.submit2.R.array.league
import com.kotlinje.submit2.activity.SearchMatchViewActivity
import com.kotlinje.submit2.activity.SearchTeamViewActivity
import com.kotlinje.submit2.adapter.TeamsAdapter
import com.kotlinje.submit2.model.liga.ResponseLiga
import com.kotlinje.submit2.model.liga.TeamsItem
import com.kotlinje.submit2.network.repository.MatchRepository
import com.kotlinje.submit2.presenter.PresenterTeams
import com.kotlinje.submit2.utility.invisible
import com.kotlinje.submit2.utility.visible
import com.kotlinje.submit2.view.LigaTeamsSearchVIiew
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by User on 01/06/2018.
 */
class LigaTeamsFagment() : android.support.v4.app.Fragment(), LigaTeamsSearchVIiew {



 override fun onDataLoaded(data: ResponseLiga?) {
    // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
 }


 override fun onDataError() {
 }

 override fun showLoadingProgress() {
     progressBar.visible()
 }

 override fun hideLoadingProgress() {
     progressBar.invisible()
 }



 override fun showEventList(data: List<TeamsItem>?) {

     teams.clear()
     if (data != null) {
         teams.addAll(data)

         Log.d("tag", "data teams :" + data)

     }
     adapterList?.notifyDataSetChanged()

 }

    var adapterList: TeamsAdapter? = null
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    var teams: MutableList<TeamsItem> = mutableListOf()
    lateinit var spinnerTeams: Spinner
    lateinit var teamsName: String
    lateinit var imgSearchMatch : ImageView

    var presenter: PresenterTeams? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_teams, container, false)
        spinnerTeams = v.findViewById(R.id.id_teams_spinner)
        val spinnerItems = resources.getStringArray(league)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinnerTeams.adapter = spinnerAdapter
        progressBar = v.findViewById(R.id.progressBar_teams)

        imgSearchMatch = v.findViewById(R.id.img_search_match)
        imgSearchMatch.setOnClickListener {

            startActivity<SearchTeamViewActivity>()
        }

        presenter = PresenterTeams(this, MatchRepository())
        var recyclerView: RecyclerView = v.findViewById(R.id.rv_teams)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        progressBar.visible()
        adapterList = TeamsAdapter(teams) {}
        recyclerView.adapter = adapterList




        spinnerTeams.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {


            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                teamsName = spinnerTeams.selectedItem.toString()

                Log.e("Tag", "spinner :" + teamsName)
                presenter?.getLigaTeamsSearch(teamsName)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }


        /*  swipeRefresh.onRefresh {
              presenter?.getLigaTeamsSearch(teamsName)
          }*/


        return v
    }
}