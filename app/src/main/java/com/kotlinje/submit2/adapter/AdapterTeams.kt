package com.kotlinje.submit2.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.kotlinje.submit2.R
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import com.kotlinje.submit2.model.liga.TeamsItem
import org.jetbrains.anko.sdk25.coroutines.onClick


class AdapterTeams(private val teams: List<TeamsItem>,
                   private val listener: (TeamsItem) -> Unit)
    : RecyclerView.Adapter<AdapterTeams.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item_team, parent, false)
        return ViewHolder(v)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(teams[position], listener)

    }

    override fun getItemCount(): Int = teams.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val teamBadge: ImageView = view.find(R.id.img_item_list_team)
        private val teamName: TextView = view.find(R.id.tv_name_item_list_team)

        fun bindItem(teams: TeamsItem, listener: (TeamsItem) -> Unit) {
            Picasso.get().load(teams.strTeamBadge).into(teamBadge)
            Log.e("Tag","get data in adapterLeagueTeams : ${teams.strTeam}")
            teamName.text = teams.strTeam
            itemView.onClick { listener(teams) }

        }
    }
}
