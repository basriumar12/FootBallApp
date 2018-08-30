package com.kotlinje.submit2.presenter

import android.util.Log
import com.kotlinje.submit2.model.event.ResponseTeam
import com.kotlinje.submit2.network.repository.MatchRepository
import com.kotlinje.submit2.network.repository.MatchRepositoryCallback
import com.kotlinje.submit2.view.MainView
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

/**
 * Created by User on 15/05/2018.
 */
// add coruntines anko
//submission 4
class PresenterMain
(private val view  : MainView, private val matchRepository: MatchRepository)
{
  /*  //get last pertandingan
   fun  getLast(ligaId :String){
        //view loading
        view.showLoadingProgress()
        val serviceGetListLiga : Servicetest = RetrofitInit.getUrl()
        //get serviceGetListLiga pada retrofit
        serviceGetListLiga.getListLast(ligaId).enqueue(object: retrofit2.Callback<ResponseTeam> {
            override fun onResponse(call: Call<ResponseTeam>?, response: Response<ResponseTeam>?) {
                view.hideLoadingProgress()


                async(UI){
                    //add anko Coroutines
                    val dataCorut = bg { response?.body()?.events
                    }

                    // add in list corutines
                    view.showEventList(dataCorut.await())
                }
            }
            override fun onFailure(call: Call<ResponseTeam>?, t: Throwable?) {

                view.hideLoadingProgress()
                view.showToast(t?.message)
            }
        })
   }
    //get next pertandingan
    fun getNext(ligaId :Int){
        //view loading
        view.showLoadingProgress()
        val serviceGetListLiga : Servicetest = RetrofitInit.getUrl()
        //get serviceGetListLiga pada retrofit
        serviceGetListLiga.getListNext(ligaId).enqueue(object: retrofit2.Callback<ResponseTeam> {
            override fun onResponse(call: Call<ResponseTeam>?, response: Response<ResponseTeam>?) {
                view.hideLoadingProgress()


                async(UI){
                    //add coruntines
                    val dataNetxLiga = bg { response?.body()?.events
                    }
                    view.showEventList(dataNetxLiga.await())
                }
            }
            override fun onFailure(call: Call<ResponseTeam>?, t: Throwable?) {

                view.hideLoadingProgress()
                view.showToast(t?.message)
            }
        })
    }*/

    fun getMatchLast(id: String) {
        view.showLoadingProgress()
        matchRepository.getLastMatch(id, object : MatchRepositoryCallback<ResponseTeam?> {
            override fun onDataLoaded(data: ResponseTeam?) {
                view.onDataLoaded(data)
                view.hideLoadingProgress()
                async(UI){
                    Log.e("data","coba"+data?.events)
                    val dataMatch = bg { data?.events }
                    Log.e("data","coba 2"+dataMatch)
                    view.showEventList(dataMatch.await())
                }

            }

            override fun onDataError() {
                view.onDataError()
                view.hideLoadingProgress()
//                view.showToast("error"+onDataError().toString())

            }
        })
        view.hideLoadingProgress()
    }

    fun getMatchNext(id: String) {
        view.showLoadingProgress()
        matchRepository.getNextMatch(id, object : MatchRepositoryCallback<ResponseTeam?> {
            override fun onDataLoaded(data: ResponseTeam?) {
                view.onDataLoaded(data)
                Log.e("data","coba next"+data?.events)
                view.hideLoadingProgress()
                async(UI){
                    val dataMatch = bg { data?.events }
                        Log.e("data","coba 2 next"+dataMatch)
                    view.showEventList(dataMatch.await())
                }

            }

            override fun onDataError() {
                view.onDataError()
                view.hideLoadingProgress()
            }
        })
        view.hideLoadingProgress()
    }



    fun thisForTesting(id: String) {
        view.showLoadingProgress()
        matchRepository.getLastMatch(id, object : MatchRepositoryCallback<ResponseTeam?> {
            override fun onDataLoaded(data: ResponseTeam?) {
                view.onDataLoaded(data)


            }

            override fun onDataError() {
                view.onDataError()
                view.hideLoadingProgress()
            }
        })
        view.hideLoadingProgress()
    }


}