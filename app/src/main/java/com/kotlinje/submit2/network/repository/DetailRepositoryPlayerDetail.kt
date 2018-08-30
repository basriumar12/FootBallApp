package com.kotlinje.submit2.network.repository

import com.kotlinje.submit2.model.detail_player.ResponseDetailPlayer
import com.kotlinje.submit2.model.player.ResponsePlayer
import com.kotlinje.submit2.network.newnetwork.MyRetrofit
import com.kotlinje.submit2.network.newnetwork.ServiceGetListLiga
import retrofit2.Call
import retrofit2.Response

/**
 * Created by User on 27/05/2018.
 */
class DetailRepositoryPlayerDetail {


    fun getDetailPlayer (id :String, callback: DetailRepositoryCallback<ResponseDetailPlayer?>){
        MyRetrofit
                .createService(ServiceGetListLiga::class.java)
                .getDetailPlayer(id)
                .enqueue(object : retrofit2.Callback<ResponseDetailPlayer> {
                    override fun onFailure(call: Call<ResponseDetailPlayer>?, t: Throwable?) {
                        callback.onDataError()
                        println("data error detail player :"+t.toString())
                        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onResponse(call: Call<ResponseDetailPlayer>?, response: Response<ResponseDetailPlayer>?) {
                   //     TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    response.let {

                        if (it!!.isSuccessful){
                            callback.onDataLoaded(it.body())
                            println("Show  detail player :"+it.body())


                        } else{
                            callback.onDataError()
                        }
                    }

                    }

                })

    }

}