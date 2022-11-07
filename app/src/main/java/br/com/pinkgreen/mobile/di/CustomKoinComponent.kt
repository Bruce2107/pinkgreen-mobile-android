package br.com.pinkgreen.mobile.di

import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent

interface CustomKoinComponent : KoinComponent {
    override fun getKoin(): Koin = SdkPinkgreenContext.koinApp!!.koin
}

object SdkPinkgreenContext {
    var koinApp: KoinApplication? = null
}