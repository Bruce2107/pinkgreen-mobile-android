package br.com.pinkgreen.mobile.init

import android.app.Application
import br.com.pinkgreen.mobile.data.Api

data class InternshipParams(
    val application: Application,
    val api: Api,
//    val theme: Int,
//    val externalRoutes: InternshipExternalRoutes,
//    val tracker: InternshipAnalytics
)