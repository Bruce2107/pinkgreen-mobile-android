package br.com.pinkgreen.mobile.init

import br.com.pinkgreen.mobile.di.SdkPinkgreenContext
import br.com.pinkgreen.mobile.di.modules.pinkgreenModule
import br.com.pinkgreen.mobile.ui.routes.PinkgreenRoute
import br.com.pinkgreen.mobile.ui.routes.PinkgreenRouteImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.dsl.koinApplication
import org.koin.dsl.module

object InternshipFeatureInitializer {
    private var koinApplication: KoinApplication? = null
    var theme: Int = org.koin.android.R.style.Base_Theme_AppCompat
    fun init(params: InternshipParams) {
//        theme = params.theme
        koinApplication = koinApplication {
            androidContext(params.application)
            modules(
                pinkgreenModule,
                module {
                    single { params.api }
//                    factory { params.externalRoutes }
//                    factory { params.tracker }
                }
            )
        }
        SdkPinkgreenContext.koinApp = koinApplication
    }

    fun getRoutes(): PinkgreenRoute = PinkgreenRouteImpl()

    fun close() {
        koinApplication?.close()
    }
}