package com.kotlinje.submit2.fragment.teams


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

import com.kotlinje.submit2.R
import com.kotlinje.submit2.activity.DetailPlayerActivity
import com.kotlinje.submit2.adapter.AdapterListDataPlayer
import com.kotlinje.submit2.model.player.PlayersItem
import com.kotlinje.submit2.model.player.ResponsePlayer
import com.kotlinje.submit2.network.repository.DetailRepositoryPlayer
import com.kotlinje.submit2.presenter.PresenterListlPlayer
import com.kotlinje.submit2.view.DetailListPlayerView
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast


/**
 * A simple [Fragment] subclass.
 * Use the [FragmentPlayerTeam.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentPlayerTeam : Fragment(), DetailListPlayerView {
    override fun showDetailListPlayer(team: List<PlayersItem>?) {
        if (team!= null){
            player.addAll(team)

        }
        adapterList?.notifyDataSetChanged()
    }

    override fun onDataLoaded(data: ResponsePlayer?) {

        showToastDetail("data responses $data")
    }


    override fun onDataError() {
        showToastDetail("error data")
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

    private var mParam1: String? = null
    private var mParam2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
       var v = inflater.inflate(R.layout.fragment_fragment_player_team, container, false)
        rvPlayer = v.find(R.id.rv_player)
        return v

    }
    var load: ProgressBar? = null
    var rvPlayer : RecyclerView? = null
    var present : PresenterListlPlayer? = null
    var player : MutableList<PlayersItem> = mutableListOf()
    var adapterList: AdapterListDataPlayer? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println(arguments?.getString(FragmentOverviewTeam.ARG_PARAM1))

        var id : String? = null

        id = arguments?.getString(FragmentOverviewTeam.ARG_PARAM1)
        rvPlayer?.layoutManager = LinearLayoutManager(activity)


        present = PresenterListlPlayer(this, DetailRepositoryPlayer())
        present?.getDetailListPlayer(id!!)
        adapterList = AdapterListDataPlayer(player){
            startActivity<DetailPlayerActivity>("idPlayer" to "${it.idPlayer}")
        }

        rvPlayer?.adapter = adapterList

    }

    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        fun newInstance(param1: String, param2: String): FragmentPlayerTeam {
            val fragment = FragmentPlayerTeam()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

}// Required empty public constructor
