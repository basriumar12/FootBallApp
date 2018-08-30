package com.kotlinje.submit2.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kotlinje.submit2.R
import com.kotlinje.submit2.model.event.ModelFavorite
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by User on 19/05/2018.
 */
// add coruntines anko
//submission 4
class AdapterFavoriteMatch(private val evenFavor:List<ModelFavorite>,
                           val listiner:(ModelFavorite)-> Unit):
        RecyclerView.Adapter<AdapterFavoriteMatch.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterFavoriteMatch.ViewHolder {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val v  : View = LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
        return AdapterFavoriteMatch.ViewHolder(v)
    }

    override fun getItemCount(): Int = evenFavor.size


    override fun onBindViewHolder(holder: AdapterFavoriteMatch.ViewHolder, position: Int) {
    //    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
           holder.bindItem(evenFavor[position],listiner)
        }


    //kelas viewholder
    class ViewHolder (view : View): RecyclerView.ViewHolder(view){
        val date: TextView = view.find(R.id.event_date)
        val teamHome: TextView = view.find(R.id.home_team)
        val teamAway: TextView = view.find(R.id.away_team)
        val scoreHome: TextView? = view.find(R.id.home_team_score)
        val scoreAway: TextView? = view.find(R.id.away_team_score)

        //bind object
        fun bindItem(event: ModelFavorite, listiner: (ModelFavorite) -> Unit) {
            date.text = event.eventDate
            teamHome.text = event.strHomeName
            teamAway.text = event.strAwayName
            scoreHome?.text = event.strScoreHome
            scoreAway?.text = event.strScoreAway
            Log.d("TAG","Adapter cek :" +event.eventDate)
            Log.d("TAG","Adapter cek :" +event.eventId)

            itemView.onClick { listiner(event) }
        }
    }
}