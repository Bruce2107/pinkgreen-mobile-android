package br.com.pinkgreen.mobile.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.pinkgreen.mobile.R
import br.com.pinkgreen.mobile.di.CustomKoinComponent

class PinkgreenActivity : AppCompatActivity(), CustomKoinComponent   {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}