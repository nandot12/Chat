package com.nandohusni.baggit.ui.youtube

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nandohusni.baggit.R
import kotlinx.android.synthetic.main.toolbar.*

class YoutubeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.youtube_activity)

        setSupportActionBar(toolbar)
        val bar = supportActionBar!!
        bar.setDisplayHomeAsUpEnabled(true)
        bar.setDisplayShowTitleEnabled(false)
        toolbar_title.setText(getString(R.string.earth))

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, YoutubeFragment.newInstance())
                .commitNow()
        }
    }

}
