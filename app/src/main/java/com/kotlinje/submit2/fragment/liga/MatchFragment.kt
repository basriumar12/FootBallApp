package com.kotlinje.submit2.fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.*
import android.widget.*
import com.kotlinje.submit2.R
import com.kotlinje.submit2.activity.SearchMatchViewActivity
import com.kotlinje.submit2.adapter.TabAdapter
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by User on 29/05/2018.
 */
class MatchFragment () : android.support.v4.app.Fragment(){



    var load : ProgressBar? = null


    lateinit var tabs : TabLayout
    lateinit var pager : ViewPager
    lateinit var imgSearchMatch : ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v: View = inflater.inflate(R.layout.fragment_match, container, false)

         imgSearchMatch = v.findViewById(R.id.img_search_match)
         tabs = v.findViewById(R.id.id_tabs)
         pager = v.findViewById(R.id.id_pager)


         imgSearchMatch.setOnClickListener {

            startActivity<SearchMatchViewActivity>()
         }


        //tvcoba.text ="Text coba"

         tabs.addTab(tabs.newTab().setText("Last Match"))
         tabs.addTab(tabs.newTab().setText("Next Match"))

        var adapter = TabAdapter(activity?.supportFragmentManager)
        pager.adapter =adapter
        pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
              //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
               // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }


            override fun onTabSelected(tab: TabLayout.Tab?) {
                pager.setCurrentItem(tab?.position!!)

                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }


        })


        return v
    }




}


