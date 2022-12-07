package br.com.pinkgreen.mkt.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.pinkgreen.mkt.R
import br.com.pinkgreen.mkt.database.MktDBQueries
import br.com.pinkgreen.mkt.di.CustomKoinComponent
import org.koin.android.ext.android.inject

internal class MktActivity : AppCompatActivity(), CustomKoinComponent {
    private val dbQueries: MktDBQueries by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mkt)
    }

    override fun onDestroy() {
        super.onDestroy()
        dbQueries.destroy()
    }
}