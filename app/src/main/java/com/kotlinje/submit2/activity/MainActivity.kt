package com.kotlinje.submit2.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.kotlinje.submit2.R
import com.kotlinje.submit2.fragment.favorite.FragmentFavoritMatch
import com.kotlinje.submit2.fragment.liga.MatchFragment
import com.kotlinje.submit2.fragment.teams.HomeDetailTeamFragment
import com.kotlinje.submit2.fragment.teams.LigaTeamsFagment
import com.kotlinje.submit2.model.event.EventLiga
import com.kotlinje.submit2.model.event.ResponseTeam
import com.kotlinje.submit2.network.repository.MatchRepository
import com.kotlinje.submit2.presenter.PresenterMain
import com.kotlinje.submit2.view.MainView
import kotlinx.android.synthetic.main.activity_main.*
// add coruntines anko
//submission 4
class MainActivity : AppCompatActivity() {

    private var menuItemDel: Menu? = null
    var present : PresenterMain? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //show fragment home
        val mgr = supportFragmentManager
        mgr.beginTransaction()
                .add(R.id.container, MatchFragment())
                .commit()
        //get konfisi fragment
        navigation_buttom.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener {

            item -> var fragment: Fragment?=null
            when (item.itemId){
                R.id.nav_last_liga -> {
                    fragment = MatchFragment()
                    showFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_next_liga -> {
                    fragment = LigaTeamsFagment()
                    showFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }R.id.nav_favorite-> {
                    fragment = HomeDetailTeamFragment()
                    showFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }


            }
            false
        });
    }

    private fun showFragment(fragment: Fragment?) {
        val mgr = supportFragmentManager
        mgr.beginTransaction().replace(R.id.container, fragment).commit()

    }


}
