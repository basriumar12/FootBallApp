package com.kotlinje.submit2.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.kotlinje.submit2.R
import com.kotlinje.submit2.model.event.ModelFavorite
import com.kotlinje.submit2.model.event.ModelFavoriteTeam
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by User on 19/05/2018.
 */
// add coruntines anko
//submission 4
class AdapterFavoriteTeam(private val evenFavor:List<ModelFavoriteTeam>,
                          val listiner:(ModelFavoriteTeam)-> Unit):
        RecyclerView.Adapter<AdapterFavoriteTeam.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterFavoriteTeam.ViewHolder {
        val v  : View = LayoutInflater.from(parent.context).inflate(R.layout.item_list_team,parent,false)
        return AdapterFavoriteTeam.ViewHolder(v)
    }

    override fun getItemCount(): Int = evenFavor.size


    override fun onBindViewHolder(holder: AdapterFavoriteTeam.ViewHolder, position: Int) {
           holder.bindItem(evenFavor[position],listiner)
        var getImageTeam = evenFavor[position].strImage
        println("get gambar in adapter :"+getImageTeam)
        Glide.with(holder.imgTeamFavorite.context).load(getImageTeam).into(holder.imgTeamFavorite)

    }


    //kelas viewholder
    class ViewHolder (view : View): RecyclerView.ViewHolder(view){
        val tvNameTeamFavorite: TextView = view.find(R.id.tv_name_team_favorite)
        val imgTeamFavorite : ImageView = view.find(R.id.img_team_favorite)

        //bind object
        fun bindItem(event: ModelFavoriteTeam, listiner: (ModelFavoriteTeam) -> Unit) {

            tvNameTeamFavorite.text = event.teamName
                      itemView.onClick { listiner(event) }
        }
    }
}