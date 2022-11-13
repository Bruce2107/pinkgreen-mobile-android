package br.com.pinkgreen.mkt.ui.viewstate

internal sealed class ErrorType {
    object Network : ErrorType()
    object Generic : ErrorType()
}