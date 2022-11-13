package br.com.pinkgreen.mkt.ui.routes

import android.content.Context
import android.content.Intent
import br.com.pinkgreen.mkt.ui.view.MktActivity

internal class MktRouteImpl : MktRoute {

    override fun getMktIntent(context: Context): Intent {
        return Intent(context, MktActivity::class.java)
    }
}