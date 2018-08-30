package com.kotlinje.submit2.network.repository

import com.kotlinje.submit2.model.event.ModelTeam
import com.kotlinje.submit2.model.event.ResponseTeam
import com.kotlinje.submit2.model.team.TeamResponse
import com.kotlinje.submit2.network.newnetwork.MyRetrofit
import com.kotlinje.submit2.network.newnetwork.ServiceGetListLiga
import retrofit2.Call
import retrofit2.Response

/**
 * Created by User on 27/05/2018.
 */
class DetailRepositoryTeamPlayer {


    fun getDetailTeam (id :String, callback: DetailRepositoryCallback<TeamResponse?>){
        MyRetrofit
                .createService(ServiceGetListLiga::class.java)
                .getOneTeam(id)
                .enqueue(object : retrofit2.Callback<TeamResponse> {
                    override fun onFailure(call: Call<TeamResponse>?, t: Throwable?) {
                        callback.onDataError()
                        println("data error team player :"+t.toString())
                        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onResponse(call: Call<TeamResponse>?, response: Response<TeamResponse>?) {
                   //     TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    response.let {

                        if (it!!.isSuccessful){
                            callback.onDataLoaded(it.body())
                            println("Show detail team player :"+it.body())


                        } else{
                            callback.onDataError()
                        }
                    }

                    }

                })

    }

}