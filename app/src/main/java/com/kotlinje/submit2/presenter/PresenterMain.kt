package com.kotlinje.submit2.presenter

import android.util.Log
import com.kotlinje.submit2.model.ResponseTeam
import com.kotlinje.submit2.network.RetrofitInit
import com.kotlinje.submit2.network.ServiceGetListLiga
import com.kotlinje.submit2.view.MainView
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import retrofit2.Call
import retrofit2.Response

/**
 * Created by User on 15/05/2018.
 */
// add coruntines anko
//submission 4
class PresenterMain
(private val view  : MainView)
{
    //get last pertandingan
   fun getLast(ligaId :Int){
        //view loading
        view.showLoadingProgress()
        val serviceGetListLiga : ServiceGetListLiga = RetrofitInit.getUrl()
        //get serviceGetListLiga pada retrofit
        serviceGetListLiga.getListLast(ligaId).enqueue(object: retrofit2.Callback<ResponseTeam> {
            override fun onResponse(call: Call<ResponseTeam>?, response: Response<ResponseTeam>?) {
                view.hideLoadingProgress()
                Log.d("Tag","get Data :"+response.toString())

                async(UI){
                    //add anko Coroutines
                    val dataCorut = bg { response?.body()?.events
                    }

                    // add in list corutines
                    view.showEventList(dataCorut.await())
                }
            }
            override fun onFailure(call: Call<ResponseTeam>?, t: Throwable?) {
                Log.d("Tag","Error Message :"+t.toString())
                view.hideLoadingProgress()
                view.showToast(t?.message)
            }
        })
   }
    //get next pertandingan
    fun getNext(ligaId :Int){
        //view loading
        view.showLoadingProgress()
        val serviceGetListLiga : ServiceGetListLiga = RetrofitInit.getUrl()
        //get serviceGetListLiga pada retrofit
        serviceGetListLiga.getListNext(ligaId).enqueue(object: retrofit2.Callback<ResponseTeam> {
            override fun onResponse(call: Call<ResponseTeam>?, response: Response<ResponseTeam>?) {
                view.hideLoadingProgress()
                Log.d("Tag","get Data :"+response.toString())

                async(UI){
                    //add coruntines
                    val dataNetxLiga = bg { response?.body()?.events
                    }
                    view.showEventList(dataNetxLiga.await())
                }
            }
            override fun onFailure(call: Call<ResponseTeam>?, t: Throwable?) {
                Log.d("Tag","Error Message :"+t.toString())
                view.hideLoadingProgress()
                view.showToast(t?.message)
            }
        })
    }

}