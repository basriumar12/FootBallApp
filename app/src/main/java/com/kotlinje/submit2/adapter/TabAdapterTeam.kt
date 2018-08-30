package com.kotlinje.submit2.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.kotlinje.submit2.fragment.teams.FragmentOverviewTeam
import com.kotlinje.submit2.fragment.teams.FragmentPlayerTeam

/**
 * Created by User on 29/05/2018.
 */
class TabAdapterTeam (fm: FragmentManager?, val idTeam : String) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            println("load overview")
            return FragmentOverviewTeam.newInstance(idTeam,"")
        } else {
            return FragmentPlayerTeam.newInstance(idTeam,"")
        }

    }

    override fun getCount(): Int {

    return 2
    }
}