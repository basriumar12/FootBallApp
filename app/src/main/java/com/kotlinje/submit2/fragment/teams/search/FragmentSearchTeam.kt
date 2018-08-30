package com.kotlinje.submit2.fragment.teams.search

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.kotlinje.submit2.R
import com.kotlinje.submit2.activity.DetailTeamActivity
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
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by User on 07/06/2018.
 */
class FragmentSearchTeam : android.support.v4.app.Fragment(), SearchTeamView {
    override fun onDataLoaded(data: ResponseSearchTeam?) {
        println("ngecek data response :$data")
    }

    override fun onDataError() {
        println("data error")
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
        }
        adapterList?.notifyDataSetChanged()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.activity_search_match, container, false)
        return v;
    }

    var load: ProgressBar? = null
    var present: PresenterSearchTeam? = null
    var adapterList: SearchTeamsAdapter? = null
    var teams: MutableList<TeamsItem> = mutableListOf()
    var hasil: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_list_match.layoutManager = LinearLayoutManager(activity)

        present = PresenterSearchTeam(this, MatchRepository())


        search_match.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }
            override fun onQueryTextChange(query: String?): Boolean {
                progressBarId.visibility = View.VISIBLE
                hasil = query!!
                if (query.length > 4) {
                    //present?.getMatchLast(hasil!!)
                    present?.getTeamsSearch(hasil!!)
                    progressBarId.visibility = View.INVISIBLE
                    adapterList = SearchTeamsAdapter(teams) {
                        startActivity<DetailTeamActivity>("idTeam" to "${it.idTeam}")

                    }
                    rv_list_match.adapter = adapterList
                }
                return true
            }
        })
    }


}