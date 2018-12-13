package com.nandohusni.baggit.ui.locations

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.nandohusni.baggit.R
import com.nandohusni.baggit.utils.Constans

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var lat: Double? = null
    private var lon: Double? = null
    private var name: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapDetail) as SupportMapFragment
        mapFragment.getMapAsync(this)

        initPindah()


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
