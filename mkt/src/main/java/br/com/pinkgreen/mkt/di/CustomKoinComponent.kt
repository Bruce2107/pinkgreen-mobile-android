package br.com.pinkgreen.mkt.di

import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent

interface CustomKoinComponent : KoinComponent {
    override fun getKoin(): Koin = SdkMktContext.koinApp!!.koin
}

object SdkMktContext {
    var koinApp: KoinApplication? = null
}