package br.com.pinkgreen.mkt.ui.routes

import android.content.Context
import android.content.Intent

interface MktRoute {
    fun getMktIntent(context: Context): Intent
}