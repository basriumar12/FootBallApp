package com.kotlinje.submit2.fragment.favorite

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlinje.submit2.R
import com.kotlinje.submit2.activity.DetailLigaActivity
import com.kotlinje.submit2.activity.DetailTeamActivity
import com.kotlinje.submit2.adapter.AdapterFavoriteMatch
import com.kotlinje.submit2.adapter.AdapterFavoriteTeam
import com.kotlinje.submit2.model.event.ModelFavorite
import com.kotlinje.submit2.model.event.ModelFavoriteTeam
import com.kotlinje.submit2.utility.database
import org.jetbrains.anko.db.rowParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by User on 19/05/2018.
 */
// add coruntines anko
//submission 4
class FragmentFavoritTeam : android.support.v4.app.Fragment() {
    var evenFavor: MutableList<ModelFavoriteTeam> = mutableListOf()
    lateinit var adapterFavoriteTeam: AdapterFavoriteTeam
    lateinit var rv : RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)

        val v: View = inflater.inflate(R.layout.fragement_favorit, container, false)

        rv = v.findViewById(R.id.rv_favorite)
        rv.layoutManager = LinearLayoutManager(v.context)

        //event klik
        adapterFavoriteTeam = AdapterFavoriteTeam(evenFavor) {
            startActivity<DetailTeamActivity>("idTeam" to "${it.teamId}")
        }

        //set adapter
        rv.adapter = adapterFavoriteTeam
        showFavoriteTeam()


        return v

    }
    //show team favorte
    private fun showFavoriteTeam() {
        activity?.database?.use {
            //buat object hasil
            val hasilData = select(ModelFavoriteTeam.TABLE_FAVORITE_TEAM)
            val  favor = hasilData.parseList(parser = rowParser{
                id:Long?, teamId :String?, teamName:String?,
                strImage:String?
                ->
                ModelFavoriteTeam(id, teamId, teamName, strImage)

            })
            //add all favorite
            evenFavor.addAll(favor)
            //set datachanged
            adapterFavoriteTeam.notifyDataSetChanged()
        }
    }
}