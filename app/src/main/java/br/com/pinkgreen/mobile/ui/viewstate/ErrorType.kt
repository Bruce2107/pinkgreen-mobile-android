package br.com.pinkgreen.mobile.ui.viewstate

sealed class ErrorType {
    object Network : ErrorType()
    object Generic : ErrorType()
}