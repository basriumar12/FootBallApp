package com.kotlinje.submit2.network

import com.kotlinje.submit2.BuildConfig.TSDB_API_KEY
import com.kotlinje.submit2.model.ModelTeam
import com.kotlinje.submit2.model.ResponseTeam
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by User on 15/05/2018.
 */
// add coruntines anko
//submission 4
interface ServiceGetListLiga {

    //end point liga last dan next
    @GET("api/v1/json/1/eventspastleague.php")
    fun getListLast(@Query("id")leagueId:String): Call<ResponseTeam>
    @GET("api/v1/json/1/eventsnextleague.php")
    fun getListNext(@Query("id")leagueId:Int): Call<ResponseTeam>
    // end point get team
    @GET("api/v1/json/1/lookupevent.php")
    fun getDataEachTeam(@Query("id")idEvent:String): Call<ResponseTeam>
    // end point get img team
    @GET("api/v1/json/1/lookupteam.php")
    fun getImgTeam(@Query("id") idTeam:String?): Call<ModelTeam>

    @GET("api/v1/json/1/searchevents.php?e=")
    fun getSearchTeamEvent(@Query("id") idTeam:String?): Call<ModelTeam>

}