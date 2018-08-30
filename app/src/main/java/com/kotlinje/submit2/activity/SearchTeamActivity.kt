package com.kotlinje.submit2.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.kotlinje.submit2.R
import com.kotlinje.submit2.fragment.teams.search.FragmentSearchTeam

class SearchTeamActivity : AppCompatActivity() {
    var fragment : Fragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_search_team)


         fragment = FragmentSearchTeam()
         showFragment(fragment)

    }

    private fun showFragment(fragment: Fragment?) {
        val mgr = supportFragmentManager
        mgr.beginTransaction().replace(R.id.linear_search_team, fragment).commit()

    }
}
