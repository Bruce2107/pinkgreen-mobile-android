package br.com.pinkgreen.mobile.lib

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val tokenType: String = "Bearer", private val accessToken: String) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        request.newBuilder().header("Authorization", "$tokenType $accessToken")
        return chain.proceed(request)
    }
}