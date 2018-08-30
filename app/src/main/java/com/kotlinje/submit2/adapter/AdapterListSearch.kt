package com.kotlinje.submit2.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kotlinje.submit2.R
import com.kotlinje.submit2.model.search.EventItem
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by User on 15/05/2018.
 */
// add coruntines anko
//submission 4
class AdapterListSearch
(private val events: List<EventItem>, private val listener: (EventItem) -> Unit) : RecyclerView.Adapter<AdapterListSearch.LastMatchViewHolder>() {
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


        fun bindItem(event: EventItem, listener: (EventItem) -> Unit) {
            date.text = event.strDate.toString()
            teamHome.text = event.strHomeTeam.toString()
            teamAway.text = event.strAwayTeam.toString()
            scoreHome?.text = event.intHomeScore.toString()
            scoreAway?.text = event.intAwayScore.toString()
            itemView.onClick { listener(event) }
        }
    }
}