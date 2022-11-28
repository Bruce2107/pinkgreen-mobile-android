package br.com.pinkgreen.mobile.lib

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(Configuration.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}