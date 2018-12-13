package com.nandohusni.baggit.ui.locations.fragment

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.nandohusni.baggit.R
import com.nandohusni.baggit.utils.Constans

import kotlinx.android.synthetic.main.activity_detail_list.*

class DetailListActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private var lat: Double? = null
    private var lon: Double? = null
    private var name: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_list)
        setSupportActionBar(toolbar)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapDetail) as SupportMapFragment
        mapFragment.getMapAsync(this)


        initPindah()
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }


    private fun initPindah() {

        lat = intent.getDoubleExtra(Constans.LAT, 0.0)
        lon = intent.getDoubleExtra(Constans.LON, 0.0)
        name = intent.getStringExtra(Constans.NAME)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = lat?.let { lon?.let { it1 -> LatLng(it, it1) } }
        mMap.addMarker(sydney?.let { MarkerOptions().position(it).title(name) })
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15f))
        mMap.uiSettings.isZoomControlsEnabled = true
    }
}
