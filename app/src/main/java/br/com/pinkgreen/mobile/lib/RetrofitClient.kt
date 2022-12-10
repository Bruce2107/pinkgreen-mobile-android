package br.com.pinkgreen.mobile.lib

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    fun getInstance(): Retrofit {
        val client =
            OkHttpClient.Builder().addInterceptor(AuthInterceptor("Bearer", Configuration.TOKEN))
                .build()
        return Retrofit.Builder().baseUrl(Configuration.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}