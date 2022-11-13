package br.com.pinkgreen.mkt.ui.components.error

internal interface ErrorLauncher {
    fun launchErrorLoadingFailure(errorLauncherParams: ErrorLauncherParams)
    fun launchErrorConnectionFailure(errorLauncherParams: ErrorLauncherParams)
}