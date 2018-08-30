package com.kotlinje.submit2.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.kotlinje.submit2.fragment.favorite.FragmentFavoritMatch
import com.kotlinje.submit2.fragment.favorite.FragmentFavoritTeam

/**
 * Created by User on 29/05/2018.
 */
class TabAdapterFavorite (fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            return FragmentFavoritMatch()
        } else {
            return FragmentFavoritTeam()
        }

    }

    override fun getCount(): Int {

    return 2
    }
}