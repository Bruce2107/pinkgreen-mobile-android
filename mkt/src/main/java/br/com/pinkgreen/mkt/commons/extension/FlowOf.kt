package br.com.pinkgreen.mkt.commons.extension

import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response

internal fun <T> flowOf(request: suspend () -> Response<T>) = flow {
    val response = request.invoke()
    if(response.isSuccessful) {
        response.body()?.let {
            emit(it)
        } ?: throw  IllegalStateException("Body not found")
    } else {
        throw HttpException(response)
    }
}