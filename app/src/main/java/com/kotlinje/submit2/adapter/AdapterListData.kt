package com.kotlinje.submit2.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kotlinje.submit2.R
import com.kotlinje.submit2.model.event.EventLiga
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick


class AdapterListData
(private val events: List<EventLiga>, private val listener: (EventLiga) -> Unit) : RecyclerView.Adapter<AdapterListData.LastMatchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastMatchViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return LastMatchViewHolder(v)

    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: LastMatchViewHolder, position: Int) {
        holder.bindItem(events[position], listener)
    }


    class LastMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val date: TextView = view.find(R.id.event_date)
        val teamHome: TextView = view.find(R.id.home_team)
        val teamAway: TextView = view.find(R.id.away_team)
        val scoreHome: TextView? = view.find(R.id.home_team_score)
        val scoreAway: TextView? = view.find(R.id.away_team_score)


        fun bindItem(event: EventLiga, listener: (EventLiga) -> Unit) {
            date.text = event.strDate
            teamHome.text = event.strHomeTeam
            teamAway.text = event.strAwayTeam
            scoreHome?.text = event.intHomeScore
            scoreAway?.text = event.intAwayScore
            itemView.onClick { listener(event) }
        }
    }
}