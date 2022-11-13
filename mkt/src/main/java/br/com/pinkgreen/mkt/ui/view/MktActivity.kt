package br.com.pinkgreen.mkt.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.pinkgreen.mkt.R
import br.com.pinkgreen.mkt.di.CustomKoinComponent

internal class MktActivity : AppCompatActivity(), CustomKoinComponent {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mkt)
    }
}