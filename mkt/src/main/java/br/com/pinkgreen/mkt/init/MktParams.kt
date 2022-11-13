package br.com.pinkgreen.mkt.init

import android.app.Application
import br.com.pinkgreen.mkt.data.MktApi

data class MktParams(
    val application: Application,
    val mktApi: MktApi,
//    val theme: Int,
//    val externalRoutes: InternshipExternalRoutes,
//    val tracker: InternshipAnalytics
)