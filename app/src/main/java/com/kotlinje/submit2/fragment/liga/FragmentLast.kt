package com.kotlinje.submit2.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.kotlinje.submit2.R
import com.kotlinje.submit2.activity.DetailLigaActivity
import com.kotlinje.submit2.adapter.AdapterListData
import com.kotlinje.submit2.model.event.EventLiga
import com.kotlinje.submit2.model.event.ResponseTeam
import com.kotlinje.submit2.network.repository.MatchRepository
import com.kotlinje.submit2.presenter.PresenterMain
import com.kotlinje.submit2.view.MainView
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

/**
 * Created by User on 15/05/2018.
 */
// add coruntines anko
//submission 4
class FragmentLast () : android.support.v4.app.Fragment(), MainView {
    override fun onDataLoaded(data: ResponseTeam?) {

        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDataError() {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var load : ProgressBar? = null
    var present : PresenterMain? =null
    var adapterList : AdapterListData? = null
    var liga : MutableList<EventLiga> = mutableListOf()
    var ligaID : String ="4328"
    var tvLastLiga : String = "Last Liga"
    var tvLiga : TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
    val v = inflater.inflate(R.layout.fragement_layout,container,false)
        var layoutLiga :RecyclerView = v.findViewById(R.id.layoutLiga)
        layoutLiga.layoutManager =LinearLayoutManager(activity)
        tvLiga = v.findViewById(R.id.tv_liga)
        present = PresenterMain(this, MatchRepository())
        present?.getMatchLast(ligaID)

        //event klik
        adapterList = AdapterListData(liga){
            startActivity<DetailLigaActivity>("idEvent" to  "${it.idEvent}")     }
        //set adapter
        layoutLiga.adapter = adapterList
        return v

    }
/*
    override fun showLiga() {

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
*/


    override fun showLoadingProgress() {
        load?.visibility = View.VISIBLE
        //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoadingProgress() {
        load?.visibility = View.INVISIBLE
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showToast(message: String?) {
        toast(message.toString())
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showEventList(data: List<EventLiga>?) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        liga.clear()
        if (data!=null){
            liga.addAll(data)
            tvLiga?.text = tvLastLiga
            Log.d("tag", "data last :"+data)

        }
        adapterList?.notifyDataSetChanged()

    }
}