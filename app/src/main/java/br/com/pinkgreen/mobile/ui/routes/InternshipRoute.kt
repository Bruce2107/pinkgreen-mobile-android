package br.com.pinkgreen.mobile.ui.routes

import android.content.Context
import android.content.Intent

interface PinkgreenRoute {
    fun getPinkgreenIntent(context: Context): Intent
}