package com.kotlinje.submit2.presenter

import android.util.Log
import com.kotlinje.submit2.model.ModelTeam
import com.kotlinje.submit2.model.ResponseTeam
import com.kotlinje.submit2.network.RetrofitInit
import com.kotlinje.submit2.network.ServiceGetListLiga
import com.kotlinje.submit2.view.DetailView
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by User on 15/05/2018.
 */

// add coruntines anko
//submission 4
class PresenterDetail
(private val view: DetailView)
{

    // get liga
    fun getLastLiga(idEvent:String){
        view.showLoadingProgress()

        val service: ServiceGetListLiga = RetrofitInit.getUrl()

        service.getDataEachTeam(idEvent).enqueue(object : Callback<ResponseTeam> {
            override fun onResponse(call: Call<ResponseTeam>?, response: Response<ResponseTeam>?) {
                view.hideLoadingProgress()
                Log.d("Tag","Data Respon"+response?.body().toString())

                async(UI){
                    val eventLastLiga = bg { response?.body()?.events?.get(0) }
                    view.showEventLiga(eventLastLiga.await())

                }

            }

            override fun onFailure(call: Call<ResponseTeam>?, t: Throwable?) {
                Log.d("TAG","Error getLastLiga :"+t.toString())
                view.hideLoadingProgress()
                view.showToastDetail(t?.message)
            }
        })

    }

    //get Home Img
    fun getHomeimg(idTeam: String?){
        view.showLoadingProgress()
        val service:ServiceGetListLiga = RetrofitInit.getUrl()
        service.getImgTeam(idTeam).enqueue(object : Callback<ModelTeam> {
            override fun onResponse(call: Call<ModelTeam>?, response: Response<ModelTeam>?) {
                Log.d("Tag","Data Respon img Home"+response?.body().toString())
                async(UI){
                    //add coruntines
                    val dataHomeImg = bg {  response?.body()?.teams?.get(0)}
                    view.showHomeTeamImg(dataHomeImg.await())
                }
            }
            override fun onFailure(call: Call<ModelTeam>?, t: Throwable?) {
                Log.d("Tag","Error getHomImg "+t.toString())
                view.hideLoadingProgress()
                view.showToastDetail(t?.message)
            }


        })

    }

    // get img away
    fun getAwayImg(idTeam: String?){
        view.showLoadingProgress()

        val service: ServiceGetListLiga = RetrofitInit.getUrl()
        service.getImgTeam(idTeam).enqueue(object : Callback<ModelTeam> {
            override fun onResponse(call: Call<ModelTeam>?, response: Response<ModelTeam>?) {
                Log.d("Tag","Data Respon img away"+response?.body().toString())
                async(UI){
                    val dataAwayImg = bg { response?.body()?.teams?.get(0) }
                    view.showAwayTeamImg(dataAwayImg.await())
                }
            }

            override fun onFailure(call: Call<ModelTeam>?, t: Throwable?) {
                Log.d("Tag","Error getAwayImg "+t.toString())
                view.hideLoadingProgress()
                view.showToastDetail(t?.message)
            }


        })

    }


}