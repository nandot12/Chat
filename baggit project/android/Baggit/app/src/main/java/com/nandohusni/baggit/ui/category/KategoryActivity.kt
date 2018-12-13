package com.nandohusni.baggit.ui.category

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import com.nandohusni.baggit.R
import com.nandohusni.baggit.ui.locations.LocationActivity
import kotlinx.android.synthetic.main.activity_kategory.*

import kotlinx.android.synthetic.main.content_kategory.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.startActivity

class KategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kategory)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        val bar = supportActionBar!!
//        bar.setDisplayHomeAsUpEnabled(true)
//        bar.setDisplayShowTitleEnabled(false)
//        toolbar_title.text = "Shopping Locations"

        initView()


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

    }

    private fun initView() {

        atm.setOnClickListener {

            pindah("atm")
        }
        bank.setOnClickListener {

            pindah("bank")
        }
        bar.setOnClickListener {

            pindah("bar")
        }
        police.setOnClickListener {

            pindah("police")
        }
        aiport.setOnClickListener {

            pindah("aiport")
        }
        restoran.setOnClickListener {
            pindah("restaurant")
        }
        cafe.setOnClickListener {

            pindah("cafe")
        }
        gym.setOnClickListener {

            pindah("gym")
        }
        aquarium.setOnClickListener {

            pindah("aquariam")
        }
        museum.setOnClickListener {

            pindah("museum")
        }
        library.setOnClickListener {

            pindah("library")
        }
        movie.setOnClickListener {

            pindah("movie_theater")
        }

        hospital.setOnClickListener {
            pindah("hospital")
        }


    }

    private fun pindah(toString: String) {

        startActivity<LocationActivity>("type" to toString)

    }

}
