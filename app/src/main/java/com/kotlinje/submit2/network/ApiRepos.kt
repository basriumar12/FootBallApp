package com.kotlinje.submit2.network

import android.util.Log
import com.kotlinje.submit2.model.event.ResponseTeam
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import retrofit2.Call
import retrofit2.Response

/**
 * Created by User on 24/05/2018.
 */
class ApiRepos (){

    fun getLast(ligaId :String){
        val servicetest : Servicetest = RetrofitInit.getUrl()
        //get servicetest pada retrofit
        servicetest.getListLast(ligaId).enqueue(object: retrofit2.Callback<ResponseTeam> {
            override fun onResponse(call: Call<ResponseTeam>?, response: Response<ResponseTeam>?) {

                Log.d("Tag","get Data :"+response.toString())

                async(UI){
                    //add anko Coroutines
                    val dataCorut = bg { response?.body()?.events
                    }

                }
            }
            override fun onFailure(call: Call<ResponseTeam>?, t: Throwable?) {
                Log.d("Tag","Error Message :"+t.toString())

            }
        })
    }


}