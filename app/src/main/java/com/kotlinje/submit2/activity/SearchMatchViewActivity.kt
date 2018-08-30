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
import com.kotlinje.submit2.adapter.AdapterListSearch
import com.kotlinje.submit2.model.event.ResponseTeam
import com.kotlinje.submit2.model.search.EventItem
import com.kotlinje.submit2.network.repository.MatchRepository
import com.kotlinje.submit2.presenter.PresenterSearch
import com.kotlinje.submit2.view.SearchMatchView
import kotlinx.android.synthetic.main.activity_search_match.*
import kotlinx.android.synthetic.main.content_search.*
import org.jetbrains.anko.startActivity

class SearchMatchViewActivity : AppCompatActivity(), SearchMatchView {
    override fun onDataLoaded(data: ResponseTeam?) {

    }

    override fun showEventList(data: List<EventItem>?) {
        liga.clear()
        if (data != null) {
            liga.addAll(data)

        }
        adapterList?.notifyDataSetChanged()
    }

    override fun onDataError() {
    }

    override fun showLoadingProgress() {
        progressBarId.visibility = View.VISIBLE
    }

    override fun hideLoadingProgress() {
        progressBarId.visibility = View.INVISIBLE
    }

    override fun showToast(message: String?) {
        showToast("Error")
    }

    var load: ProgressBar? = null
    var present: PresenterSearch? = null
    var adapterList: AdapterListSearch? = null
    var liga: MutableList<EventItem> = mutableListOf()
    var hasil: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)
        setSupportActionBar(toolbar)
        rv_list_match.layoutManager = LinearLayoutManager(this@SearchMatchViewActivity)

        present = PresenterSearch(this, MatchRepository())


        search_match.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                progressBarId.visibility = View.VISIBLE
                hasil = query!!

                if (query.length > 4) {
                    present?.getMatchSearch(hasil!!)
                    progressBarId.visibility = View.INVISIBLE
                    adapterList = AdapterListSearch(liga) {
                        startActivity<DetailLigaActivity>("idEvent" to it.idEvent)
                    }
                    rv_list_match.adapter = adapterList
                }

                return true
            }
        })


    }
}
