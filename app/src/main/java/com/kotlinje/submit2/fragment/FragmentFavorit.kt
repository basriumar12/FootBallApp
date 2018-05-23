package com.kotlinje.submit2.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlinje.submit2.R
import com.kotlinje.submit2.activity.DetailActivity
import com.kotlinje.submit2.adapter.AdapterFavorite
import com.kotlinje.submit2.model.ModelFavorite
import com.kotlinje.submit2.utility.database
import org.jetbrains.anko.db.rowParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by User on 19/05/2018.
 */
// add coruntines anko
//submission 4
class FragmentFavorit : android.support.v4.app.Fragment() {
    var evenFavor: MutableList<ModelFavorite> = mutableListOf()
    lateinit var adapterFavorite: AdapterFavorite
    lateinit var rv : RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)

        val v: View = inflater.inflate(R.layout.fragement_favorit, container, false)

        rv = v.findViewById(R.id.rv_favorite)
        rv.layoutManager = LinearLayoutManager(v.context)

        //event klik
        adapterFavorite = AdapterFavorite(evenFavor) {
            startActivity<DetailActivity>("idEvent" to "${it.eventId}")
        }

        //set adapter
        rv.adapter = adapterFavorite
        showFavoriteTeam()


        return v

    }
    //show team favorte
    private fun showFavoriteTeam() {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        activity?.database?.use {
            //buat object hasil
            val hasilData = select(ModelFavorite.TABLE_FAVORITE)
            val  favor = hasilData.parseList(parser = rowParser{
                id:Long?, evenId :String?, evenTgl:String?,
                        homeName:String?,awayName:String?,
                        scoreHome:String?,scoreAway:String?->
                ModelFavorite(id,evenId,evenTgl,homeName,awayName,scoreHome,scoreAway)

            })
            //add all favorite
            evenFavor.addAll(favor)
            //set datachanged
            adapterFavorite.notifyDataSetChanged()
        }

    }
}