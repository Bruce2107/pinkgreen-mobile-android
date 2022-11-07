package br.com.pinkgreen.mobile.commons.extension

import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response

fun <T> flowOf(request: suspend () -> Response<T>) = flow {
    val response = request.invoke()
    if(response.isSuccessful) {
        response.body()?.let {
            emit(it)
        } ?: throw  IllegalStateException("Body not found")
    } else {
        throw HttpException(response)
    }
}