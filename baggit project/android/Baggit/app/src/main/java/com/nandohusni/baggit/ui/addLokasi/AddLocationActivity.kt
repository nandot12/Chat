package com.nandohusni.baggit.ui.addLokasi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nandohusni.baggit.R
import com.nandohusni.baggit.ui.addLokasi.ui.addlocation.AddLocationFragment
import kotlinx.android.synthetic.main.toolbar.*

class AddLocationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_location_activity)

        setSupportActionBar(toolbar)
        val bar = supportActionBar!!
        bar.setDisplayHomeAsUpEnabled(true)
        bar.setDisplayShowTitleEnabled(false)
        toolbar_title.text = getString(R.string.add_locations)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AddLocationFragment.newInstance())
                .commitNow()
        }
    }

}
