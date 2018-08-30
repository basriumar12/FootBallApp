package com.kotlinje.submit2.fragment.teams


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView

import com.kotlinje.submit2.R
import com.kotlinje.submit2.model.team.Team
import com.kotlinje.submit2.model.team.TeamResponse
import com.kotlinje.submit2.network.repository.DetailRepositoryTeamPlayer
import com.kotlinje.submit2.presenter.PresenterDetailTeam
import com.kotlinje.submit2.view.DetailTeamView
import org.jetbrains.anko.support.v4.toast


class FragmentOverviewTeam : Fragment(), DetailTeamView {
    override fun onDataLoaded(data: TeamResponse?) {
      showToastDetail("hasil responsose $data")
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

    override fun showDetailTeam(team: Team?) {

        modelTeam =team

        var textOverviewTeam = team?.teamDescription
        textOverview?.text = textOverviewTeam

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
        val v = inflater.inflate(R.layout.fragment_overview, container, false)
        textOverview = v.findViewById(R.id.tv_overview_team_player)

        return v
    }
    var load: ProgressBar? = null
    var  textOverview : TextView? = null
    var modelTeam : Team? = null
    var present : PresenterDetailTeam? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var id : String? = null
        id = arguments?.getString(ARG_PARAM1)
        modelTeam = Team()
        present = PresenterDetailTeam(this, DetailRepositoryTeamPlayer())
        present?.getDetailTeam(id!!)
    }

    companion object {
        val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        fun newInstance(param1: String, param2: String): FragmentOverviewTeam {
            val fragment = FragmentOverviewTeam()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

}
