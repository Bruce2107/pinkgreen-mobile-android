package br.com.pinkgreen.mobile.main.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.pinkgreen.mobile.R

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
    }
}