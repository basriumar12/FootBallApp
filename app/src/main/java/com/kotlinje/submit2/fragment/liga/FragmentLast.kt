package com.kotlinje.submit2.fragment.liga

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.kotlinje.submit2.R
import com.kotlinje.submit2.activity.DetailLigaActivity
import com.kotlinje.submit2.adapter.AdapterListData
import com.kotlinje.submit2.model.event.EventLiga
import com.kotlinje.submit2.model.event.ResponseTeam
import com.kotlinje.submit2.network.repository.MatchRepository
import com.kotlinje.submit2.presenter.PresenterMain
import com.kotlinje.submit2.view.MainView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast


class FragmentLast() : android.support.v4.app.Fragment(), MainView {
    override fun onDataLoaded(data: ResponseTeam?) {
    }

    override fun onDataError() {
    }

    var load: ProgressBar? = null
    var present: PresenterMain? = null
    var adapterList: AdapterListData? = null
    var liga: MutableList<EventLiga> = mutableListOf()
    var tvLiga: TextView? = null
    lateinit var spinnerTeams: Spinner
    lateinit var teamsName: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        val v = inflater.inflate(R.layout.fragement_layout, container, false)
        var layoutLiga: RecyclerView = v.findViewById(R.id.layoutLiga)
        spinnerTeams = v.findViewById(R.id.id_teams_spinner)

        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinnerTeams.adapter = spinnerAdapter

        layoutLiga.layoutManager = LinearLayoutManager(activity)
        present = PresenterMain(this, MatchRepository())

        //event klik
        adapterList = AdapterListData(liga) {
            startActivity<DetailLigaActivity>("idEvent" to "${it.idEvent}")
        }
        //set adapter
        layoutLiga.adapter = adapterList

        spinnerTeams.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {


            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                teamsName = spinnerTeams.selectedItem.toString()
                teamsName ="4328"

                if (position== 0){
                    teamsName ="4328"
                }
                else if (position==1){
                    teamsName="4329"
                } else if (position==2){
                    teamsName="4331"
                } else if (position==3){
                    teamsName="4332"
                } else if (position==4){
                    teamsName="4334"
                } else if (position==5){
                    teamsName="4335"
                }

                Log.e("Tag", "spinner :" + teamsName)
                present?.getMatchLast(teamsName)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }

        return v

    }



    override fun showLoadingProgress() {
        load?.visibility = View.VISIBLE
    }

    override fun hideLoadingProgress() {
        load?.visibility = View.INVISIBLE
    }

    override fun showToast(message: String?) {
        toast(message.toString())
    }

    override fun showEventList(data: List<EventLiga>?) {
        liga.clear()
        if (data != null) {
            liga.addAll(data)

        }
        adapterList?.notifyDataSetChanged()

    }
}