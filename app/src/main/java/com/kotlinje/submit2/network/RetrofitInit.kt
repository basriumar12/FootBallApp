package com.kotlinje.submit2.network

import com.google.gson.GsonBuilder
import com.kotlinje.submit2.BuildConfig.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by User on 15/05/2018.
 */

// add coruntines anko
//submission 4
//for panggil koneksi
object RetrofitInit {
    val gson = GsonBuilder().setLenient().create()

    val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    fun getUrl():ServiceGetListLiga = retrofit.create(ServiceGetListLiga::class.java)
}