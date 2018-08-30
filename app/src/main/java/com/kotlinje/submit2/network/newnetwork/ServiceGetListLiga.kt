package com.kotlinje.submit2.network.newnetwork

import com.kotlinje.submit2.model.detail_player.ResponseDetailPlayer
import com.kotlinje.submit2.model.event.ModelTeam
import com.kotlinje.submit2.model.event.ResponseTeam
import com.kotlinje.submit2.model.liga.ResponseLiga
import com.kotlinje.submit2.model.player.ResponsePlayer
import com.kotlinje.submit2.model.search.ResponseSearch
import com.kotlinje.submit2.model.search.ResponseSearchTeam
import com.kotlinje.submit2.model.team.TeamResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by User on 15/05/2018.
 */

interface ServiceGetListLiga {

    //end point liga last dan next
    @GET("api/v1/json/1/eventspastleague.php")
    fun getListLast(@Query("id")league:String): Call<ResponseTeam>
    @GET("api/v1/json/1/eventsnextleague.php")
    fun getListNext(@Query("id")leagueId:String): Call<ResponseTeam>

    @GET("api/v1/json/1/searchevents.php")
    fun getListSearch(@Query("e")e:String): Call<ResponseSearch>
    // end point get team
    @GET("api/v1/json/1/lookupevent.php")
    fun getDataEachTeam(@Query("id")idEvent:String): Call<ResponseTeam>
    // end point get img team
    @GET("api/v1/json/1/lookupteam.php")
    fun getImgTeam(@Query("id") idTeam:String?): Call<ModelTeam>

    @GET("api/v1/json/1/search_all_teams.php")
    fun getTeams(@Query("l") league: String?): Call<ResponseLiga>

    @GET("api/v1/json/1/searchteams.php")
    fun getSearchTeam(@Query("t") league: String?): Call<ResponseSearchTeam>

    @GET("api/v1/json/1/lookupteam.php")
    fun getOneTeam(@Query("id") idTeam:String?): Call<TeamResponse>

    @GET("api/v1/json/1/lookup_all_players.php")
    fun getTeamPlayer(@Query("id") idTeam:String?): Call<ResponsePlayer>

     @GET("api/v1/json/1/lookupplayer.php")
    fun getDetailPlayer(@Query("id") idTeam:String?): Call<ResponseDetailPlayer>


    //base url

}