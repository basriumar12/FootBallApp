package com.kotlinje.submit2.network.repository

import android.util.Log
import com.kotlinje.submit2.model.EventLiga
import com.kotlinje.submit2.model.ResponseTeam
import com.kotlinje.submit2.network.newnetwork.MyRetrofit
import com.kotlinje.submit2.network.newnetwork.ServiceGetListLiga
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

/**
 * Created by User on 27/05/2018.
 */
class MatchRepository {
    fun getLastMatch (id :Int, callback: MatchRepositoryCallback<ResponseTeam?>){
        MyRetrofit
                .createService(ServiceGetListLiga::class.java)
                .getListLast(id)
                .enqueue(object : retrofit2.Callback<ResponseTeam> {
                    override fun onFailure(call: Call<ResponseTeam>?, t: Throwable?) {
                        callback.onDataError()
                        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onResponse(call: Call<ResponseTeam>?, response: Response<ResponseTeam>?) {
                   //     TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    response.let {

                        if (it!!.isSuccessful){
                            callback.onDataLoaded(it.body())

                        } else{
                            callback.onDataError()
                        }
                    }

                    }

                })

    }
    fun getNextMatch (id :Int, callback: MatchRepositoryCallback<ResponseTeam?>){
        MyRetrofit
                .createService(ServiceGetListLiga::class.java)
                .getListNext(id)
                .enqueue(object : retrofit2.Callback<ResponseTeam> {
                    override fun onFailure(call: Call<ResponseTeam>?, t: Throwable?) {
                        callback.onDataError()

                    }

                    override fun onResponse(call: Call<ResponseTeam>?, response: Response<ResponseTeam>?) {
                     response.let {

                        if (it!!.isSuccessful){
                            callback.onDataLoaded(it.body())
                            Log.e("data","berhasil"+response?.body()?.events)



                        } else{
                            callback.onDataError()
                        }
                    }

                    }

                })

    }
}