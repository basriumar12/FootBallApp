package com.kotlinje.submit2.network.repository

import com.kotlinje.submit2.model.player.ResponsePlayer
import com.kotlinje.submit2.network.newnetwork.MyRetrofit
import com.kotlinje.submit2.network.newnetwork.ServiceGetListLiga
import retrofit2.Call
import retrofit2.Response

/**
 * Created by User on 27/05/2018.
 */
class DetailRepositoryPlayer {


    fun getDetailListPlayer (id :String, callback: DetailRepositoryCallback<ResponsePlayer?>){
        MyRetrofit
                .createService(ServiceGetListLiga::class.java)
                .getTeamPlayer(id)
                .enqueue(object : retrofit2.Callback<ResponsePlayer> {
                    override fun onFailure(call: Call<ResponsePlayer>?, t: Throwable?) {
                        callback.onDataError()
                        println("data error team player :"+t.toString())
                        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onResponse(call: Call<ResponsePlayer>?, response: Response<ResponsePlayer>?) {
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