package com.kotlinje.submit2.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.kotlinje.submit2.R.id.team_badge
import com.kotlinje.submit2.R.id.team_name
import com.kotlinje.submit2.model.liga.TeamsItem
import com.kotlinje.submit2.model.team.Team
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by User on 02/06/2018.
 */
class SearchTeamsAdapter(private val teams: List<com.kotlinje.submit2.model.search.TeamsItem>, private val listener: (com.kotlinje.submit2.model.search.TeamsItem) -> Unit)
    : RecyclerView.Adapter<TeamViewHoldera>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHoldera {
        return TeamViewHoldera(TeamUIa().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: TeamViewHoldera, position: Int) {
        holder.bindItem(teams[position], listener)
    }

    override fun getItemCount(): Int = teams.size

}

class TeamUIa : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                imageView {
                    id = team_badge
                }.lparams{
                    height = dip(50)
                    width = dip(50)
                }

                textView {
                    id = team_name
                    textSize = 16f
                }.lparams{
                    margin = dip(15)
                }

            }
        }
    }

}

class TeamViewHoldera(view: View) : RecyclerView.ViewHolder(view){

    private val teamBadge: ImageView = view.find(team_badge)
    private val teamName: TextView = view.find(team_name)

    fun bindItem(teams: com.kotlinje.submit2.model.search.TeamsItem, listener: (com.kotlinje.submit2.model.search.TeamsItem) -> Unit) {
        Picasso.get().load(teams.strTeamBadge).into(teamBadge)
        teamName.text = teams.strTeam
        itemView.onClick { listener(teams) }
    }
}