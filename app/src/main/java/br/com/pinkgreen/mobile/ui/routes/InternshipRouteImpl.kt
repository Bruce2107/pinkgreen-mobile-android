package br.com.pinkgreen.mobile.ui.routes

import android.content.Context
import android.content.Intent
import br.com.pinkgreen.mobile.ui.view.PinkgreenActivity

internal class PinkgreenRouteImpl : PinkgreenRoute {

    override fun getPinkgreenIntent(context: Context): Intent {
        return Intent(context, PinkgreenActivity::class.java)
    }
}