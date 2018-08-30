package com.kotlinje.submit2.network.newnetwork

import com.kotlinje.submit2.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by User on 27/05/2018.
 */
object MyRetrofit {
    private fun iniRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun <T> createService(service: Class<T>): T {
        return iniRetrofit().create(service)
    }
}