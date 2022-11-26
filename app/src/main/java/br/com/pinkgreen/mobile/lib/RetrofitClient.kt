package br.com.pinkgreen.mobile.lib

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
//    private const val baseUrl = "http://192.168.100.2:8181"
    private const val baseUrl = "http://10.0.2.2:8181"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}