package com.kotlinje.submit2.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.kotlinje.submit2.R
import com.kotlinje.submit2.R.menu.navigation
import com.kotlinje.submit2.fragment.FragmentFavorit
import com.kotlinje.submit2.fragment.FragmentLast
import com.kotlinje.submit2.fragment.FragmentNext
import kotlinx.android.synthetic.main.activity_main.*
// add coruntines anko
//submission 4
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //show fragment home
        val mgr = supportFragmentManager
        mgr.beginTransaction()
                .add(R.id.container,FragmentLast())
                .commit()
        //get konfisi fragment
        navigation_buttom.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener {

            item -> var fragment: Fragment?=null
            when (item.itemId){
                R.id.nav_last_liga -> {
                    fragment = FragmentLast()
                    showFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_next_liga -> {
                    fragment = FragmentNext()
                    showFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }R.id.nav_favorite-> {
                    fragment = FragmentFavorit()
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
