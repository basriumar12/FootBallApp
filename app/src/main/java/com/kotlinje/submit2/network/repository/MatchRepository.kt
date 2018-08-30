package com.kotlinje.submit2.network.repository

import com.kotlinje.submit2.model.event.ResponseTeam
import com.kotlinje.submit2.model.liga.ResponseLiga
import com.kotlinje.submit2.model.search.ResponseSearch
import com.kotlinje.submit2.model.search.ResponseSearchTeam
import com.kotlinje.submit2.network.newnetwork.MyRetrofit
import com.kotlinje.submit2.network.newnetwork.ServiceGetListLiga
import retrofit2.Call
import retrofit2.Response

/**
 * Created by User on 27/05/2018.
 */
class MatchRepository {
    fun getLastMatch(id: String, callback: MatchRepositoryCallback<ResponseTeam?>) {
        MyRetrofit
                .createService(ServiceGetListLiga::class.java)
                .getListLast(id)
                .enqueue(object : retrofit2.Callback<ResponseTeam> {
                    override fun onFailure(call: Call<ResponseTeam>?, t: Throwable?) {
                        callback.onDataError()

                    }

                    override fun onResponse(call: Call<ResponseTeam>?, response: Response<ResponseTeam>?) {
                        response.let {

                            if (it!!.isSuccessful) {
                                callback.onDataLoaded(it.body())

                            } else {
                                callback.onDataError()
                            }
                        }

                    }

                })

    }

    fun getNextMatch(id: String, callback: MatchRepositoryCallback<ResponseTeam?>) {
        MyRetrofit
                .createService(ServiceGetListLiga::class.java)
                .getListNext(id)
                .enqueue(object : retrofit2.Callback<ResponseTeam> {
                    override fun onFailure(call: Call<ResponseTeam>?, t: Throwable?) {
                        callback.onDataError()

                    }

                    override fun onResponse(call: Call<ResponseTeam>?, response: Response<ResponseTeam>?) {
                        response.let {

                            if (it!!.isSuccessful) {
                                callback.onDataLoaded(it.body())


                            } else {
                                callback.onDataError()
                            }
                        }

                    }

                })

    }

    fun getSearchMatch(id: String, callback: SearchRepositoryCallback<ResponseSearch?>) {
        MyRetrofit
                .createService(ServiceGetListLiga::class.java)
                .getListSearch(id)
                .enqueue(object : retrofit2.Callback<ResponseSearch> {
                    override fun onFailure(call: Call<ResponseSearch>?, t: Throwable?) {
                        callback.onDataError()

                    }

                    override fun onResponse(call: Call<ResponseSearch>?, response: Response<ResponseSearch>?) {
                        response.let {

                            if (it!!.isSuccessful) {
                                callback.onDataLoaded(it.body())


                            } else {
                                callback.onDataError()
                            }
                        }

                    }
                })

    }

    fun getSearchLigaTeams(id: String, callbackLiga: LigaTeamsRepositoryCallback<ResponseLiga?>) {
        MyRetrofit
                .createService(ServiceGetListLiga::class.java)
                .getTeams(id)
                .enqueue(object : retrofit2.Callback<ResponseLiga> {
                    override fun onFailure(call: Call<ResponseLiga>?, t: Throwable?) {
                        callbackLiga.onDataError()

                    }

                    override fun onResponse(call: Call<ResponseLiga>?, response: Response<ResponseLiga>?) {
                        response.let {

                            if (it!!.isSuccessful) {
                                callbackLiga.onDataLoaded(it.body())

                            } else {
                                callbackLiga.onDataError()
                            }
                        }

                    }


                })

    }


    fun getSearchTeams(id: String, callbackLiga: SearchTeamRepositoryCallback<ResponseSearchTeam?>) {
        MyRetrofit
                .createService(ServiceGetListLiga::class.java)
                .getSearchTeam(id)
                .enqueue(object : retrofit2.Callback<ResponseSearchTeam> {
                    override fun onFailure(call: Call<ResponseSearchTeam>?, t: Throwable?) {
                        callbackLiga.onDataError()

                    }

                    override fun onResponse(call: Call<ResponseSearchTeam>?, response: Response<ResponseSearchTeam>?) {
                        response.let {

                            if (it!!.isSuccessful) {
                                callbackLiga.onDataLoaded(it.body())

                            } else {
                                callbackLiga.onDataError()
                            }
                        }

                    }


                })

    }


}
