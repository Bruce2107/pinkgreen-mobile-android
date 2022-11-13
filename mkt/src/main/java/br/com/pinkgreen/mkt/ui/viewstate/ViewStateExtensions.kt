package br.com.pinkgreen.mkt.ui.viewstate

import kotlinx.coroutines.flow.*
import java.net.SocketTimeoutException
import java.net.UnknownHostException

internal suspend fun <T> Flow<T>.collectViewState(viewState: MutableStateFlow<ViewState<T>?>) =
    onStart {
        viewState.value = ViewState.OnLoading
    }.catch {
        if (it.isConnectionError()) {
            viewState.value = ViewState.OnError(ErrorType.Network)
        } else {
            viewState.value = ViewState.OnError(ErrorType.Generic)
        }
    }.collect {
        viewState.value = ViewState.OnSuccess(it)
    }

internal suspend fun <T> StateFlow<ViewState<T>?>.collectIfNotNull(action: (ViewState<T>) -> (Unit)) {
    collect {
        it?.let {
            action.invoke(it)
        }
    }
}

internal fun Throwable.isConnectionError() =
    cause is UnknownHostException || this is UnknownHostException ||
            cause is SocketTimeoutException || this is SocketTimeoutException