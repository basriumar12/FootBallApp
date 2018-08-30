package com.kotlinje.submit2.fragment.teams

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.kotlinje.submit2.R
import com.kotlinje.submit2.adapter.TabAdapter
import com.kotlinje.submit2.adapter.TabAdapterFavorite

class HomeDetailTeamFragment() : android.support.v4.app.Fragment(){
    lateinit var tabs: TabLayout
    lateinit var pager: ViewPager
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_team, container, false)
        tabs = v.findViewById(R.id.id_tabs)
        pager = v.findViewById(R.id.id_pager)

        return v

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabs.addTab(tabs.newTab().setText("Favorite Match"))
        tabs.addTab(tabs.newTab().setText("Favorite Team"))
        var adapter = TabAdapterFavorite(activity?.supportFragmentManager)
        pager.adapter = adapter
        pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabSelected(tab: TabLayout.Tab?) {
                //pager.setCurrentItem(tab?.position!!)
                if (tab != null) {
                    pager.currentItem = tab.position
                }
            }


        })



    }

}