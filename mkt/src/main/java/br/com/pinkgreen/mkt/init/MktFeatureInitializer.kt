package br.com.pinkgreen.mkt.init

import br.com.pinkgreen.mkt.di.SdkMktContext
import br.com.pinkgreen.mkt.di.modules.mktModule
import br.com.pinkgreen.mkt.ui.routes.MktRoute
import br.com.pinkgreen.mkt.ui.routes.MktRouteImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.dsl.koinApplication
import org.koin.dsl.module

object MktFeatureInitializer {
    private var koinApplication: KoinApplication? = null
    var theme: Int = org.koin.android.R.style.Base_Theme_AppCompat
    fun init(params: MktParams) {
//        theme = params.theme
        koinApplication = koinApplication {
            androidContext(params.application)
            modules(
                mktModule,
                module {
                    single { params.mktApi }
//                    factory { params.externalRoutes }
//                    factory { params.tracker }
                }
            )
        }
        SdkMktContext.koinApp = koinApplication
    }

    fun getRoutes(): MktRoute = MktRouteImpl()

    fun close() {
        koinApplication?.close()
    }
}