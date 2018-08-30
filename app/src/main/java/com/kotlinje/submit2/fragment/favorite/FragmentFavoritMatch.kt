package com.kotlinje.submit2.fragment.favorite

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlinje.submit2.R
import com.kotlinje.submit2.activity.DetailLigaActivity
import com.kotlinje.submit2.adapter.AdapterFavoriteMatch
import com.kotlinje.submit2.model.event.ModelFavorite
import com.kotlinje.submit2.utility.database
import org.jetbrains.anko.db.rowParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.startActivity

class FragmentFavoritMatch : android.support.v4.app.Fragment() {
    var evenFavor: MutableList<ModelFavorite> = mutableListOf()
    lateinit var adapterFavoriteMatch: AdapterFavoriteMatch
    lateinit var rv: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragement_favorit, container, false)

        rv = v.findViewById(R.id.rv_favorite)
        rv.layoutManager = LinearLayoutManager(v.context)
        //event klik
        adapterFavoriteMatch = AdapterFavoriteMatch(evenFavor) {
            startActivity<DetailLigaActivity>("idEvent" to "${it.eventId}")
        }

        //set adapter
        rv.adapter = adapterFavoriteMatch
        showFavoriteTeam()


        return v

    }

    //show team favorte
    private fun showFavoriteTeam() {
        activity?.database?.use {
            //buat object hasil
            val hasilData = select(ModelFavorite.TABLE_FAVORITE)
            val favor = hasilData.parseList(parser = rowParser { id: Long?, evenId: String?, evenTgl: String?,
                                                                 homeName: String?, awayName: String?,
                                                                 scoreHome: String?, scoreAway: String? ->
                ModelFavorite(id, evenId, evenTgl, homeName, awayName, scoreHome, scoreAway)

            })
            //add all favorite
            evenFavor.addAll(favor)
            //set datachanged
            adapterFavoriteMatch.notifyDataSetChanged()
        }

    }
}