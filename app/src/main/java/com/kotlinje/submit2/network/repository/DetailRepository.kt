package com.kotlinje.submit2.network.repository

import com.kotlinje.submit2.model.event.ModelTeam
import com.kotlinje.submit2.model.event.ResponseTeam
import com.kotlinje.submit2.network.newnetwork.MyRetrofit
import com.kotlinje.submit2.network.newnetwork.ServiceGetListLiga
import retrofit2.Call
import retrofit2.Response

/**
 * Created by User on 27/05/2018.
 */
class DetailRepository {
    fun getLastMatch (id :String, callback: DetailRepositoryCallback<ResponseTeam?>){
        MyRetrofit
                .createService(ServiceGetListLiga::class.java)
                .getDataEachTeam(id)
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

    fun getImgHome (id :String, callback: DetailRepositoryCallback<ModelTeam?>){
        MyRetrofit
                .createService(ServiceGetListLiga::class.java)
                .getImgTeam(id)
                .enqueue(object : retrofit2.Callback<ModelTeam> {
                    override fun onFailure(call: Call<ModelTeam>?, t: Throwable?) {
                        callback.onDataError()
                        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onResponse(call: Call<ModelTeam>?, response: Response<ModelTeam>?) {
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

    fun getImgAway (id :String, callback: DetailRepositoryCallback<ModelTeam?>){
        MyRetrofit
                .createService(ServiceGetListLiga::class.java)
                .getImgTeam(id)
                .enqueue(object : retrofit2.Callback<ModelTeam> {
                    override fun onFailure(call: Call<ModelTeam>?, t: Throwable?) {
                        callback.onDataError()
                        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onResponse(call: Call<ModelTeam>?, response: Response<ModelTeam>?) {
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

}