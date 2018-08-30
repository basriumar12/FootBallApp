package com.kotlinje.submit2.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.kotlinje.submit2.R
import com.kotlinje.submit2.R.id.img_detail_gambar
import com.kotlinje.submit2.R.id.img_team_player
import com.kotlinje.submit2.model.event.EventLiga
import com.kotlinje.submit2.model.player.PlayersItem
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import kotlin.coroutines.experimental.coroutineContext

/**
 * Created by User on 15/05/2018.
 */
// add coruntines anko
//submission 4
class AdapterListDataPlayer
(private val events:List<PlayersItem>, private val listener :(PlayersItem)->Unit ) : RecyclerView.Adapter<AdapterListDataPlayer.LastMatchViewHolder>()

{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastMatchViewHolder {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val v  : View =LayoutInflater.from(parent.context).inflate(R.layout.item_list_player,parent,false)
        return LastMatchViewHolder(v)

    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder:LastMatchViewHolder, position: Int) {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        holder.bindItem(events[position],listener)
        var getImageTeam = events[position].strThumb
        println("get gambar in adapter :"+getImageTeam)
       Glide.with(holder.imgTeamPlayer.context).load(getImageTeam).into(holder.imgTeamPlayer)


    }


    class LastMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvNamePlayer: TextView = view.find(R.id.tv_name_player)
        val tvPosisitionPlayer: TextView = view.find(R.id.tv_posisition_player)
        val imgTeamPlayer : ImageView = view.find(R.id.img_team_player)


        fun bindItem(event: PlayersItem, listener: (PlayersItem) -> Unit) {

        tvNamePlayer.text = event.strPlayer
       tvPosisitionPlayer.text = event.strPosition
            //Glide.with().load(getImageTeam).into(imgTeamPlayer)
            Log.d("TAG","Adapter cek :" +event.strPlayer)

            itemView.onClick { listener(event) }
        }
    }
}