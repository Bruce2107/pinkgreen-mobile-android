package br.com.pinkgreen.mobile.main.ui.view

import android.content.Context
import br.com.pinkgreen.mkt.init.MktFeatureInitializer

fun Context.startInternshipFeature() = MktFeatureInitializer
    .getRoutes()
    .getMktIntent(this)
    .run(::startActivity)
