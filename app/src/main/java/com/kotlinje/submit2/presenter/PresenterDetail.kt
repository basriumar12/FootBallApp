package com.kotlinje.submit2.presenter

import com.kotlinje.submit2.model.event.ModelTeam
import com.kotlinje.submit2.model.event.ResponseTeam
import com.kotlinje.submit2.network.repository.DetailRepository
import com.kotlinje.submit2.network.repository.DetailRepositoryCallback
import com.kotlinje.submit2.view.DetailView
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

/**
 * Created by User on 15/05/2018.
 */

// add coruntines anko
//submission 4
class PresenterDetail
(private val view: DetailView, private val detailRepository: DetailRepository)
{

    fun getMatchLast(idEvent: String) {
        view.showLoadingProgress()
        detailRepository.getLastMatch(idEvent, object : DetailRepositoryCallback<ResponseTeam?> {
            override fun onDataLoaded(data: ResponseTeam?) {
                view.hideLoadingProgress()
                async(UI){

                  val dataMatch = bg { data?.events?.get(0) }
                    view.showEventLiga(dataMatch.await())
                }
            }

            override fun onDataError() {
                view.onDataError()
                view.hideLoadingProgress()
            }
        })
        view.hideLoadingProgress()
    }

    fun getImgHome(idTeam: String) {
        view.showLoadingProgress()
        detailRepository.getImgHome(idTeam, object : DetailRepositoryCallback<ModelTeam?> {
            override fun onDataLoaded(data: ModelTeam?) {
                view.hideLoadingProgress()
                async(UI){

                  val dataImgHome = bg { data?.teams?.get(0) }
                    view.showHomeTeamImg(dataImgHome.await())
                }
            }

            override fun onDataError() {
                view.onDataError()
                view.hideLoadingProgress()
            }
        })
        view.hideLoadingProgress()
    }
    fun getImgAway(idTeam: String) {
        view.showLoadingProgress()
        detailRepository.getImgAway(idTeam, object : DetailRepositoryCallback<ModelTeam?> {
            override fun onDataLoaded(data: ModelTeam?) {
                view.hideLoadingProgress()
                async(UI){

                  val dataImgAway = bg { data?.teams?.get(0) }
                    view.showAwayTeamImg(dataImgAway.await())
                }
            }

            override fun onDataError() {
                view.onDataError()
                view.hideLoadingProgress()
            }
        })
        view.hideLoadingProgress()
    }
    fun forTesting(idEvent: String) {
        view.showLoadingProgress()
        detailRepository.getImgAway(idEvent, object : DetailRepositoryCallback<ModelTeam?> {
            override fun onDataLoaded(data: ModelTeam?) {


            }

            override fun onDataError() {
                view.onDataError()
                view.hideLoadingProgress()
            }
        })

    }


/*    // get liga
    fun getLastLiga(idEvent:String){
        view.showLoadingProgress()

        val service: Servicetest = RetrofitInit.getUrl()

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
        val service:Servicetest = RetrofitInit.getUrl()
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

        val service: Servicetest = RetrofitInit.getUrl()
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

    }*/


}