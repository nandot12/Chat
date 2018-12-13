package com.nandohusni.baggit.ui.locations.fragment

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nandohusni.baggit.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.nandohusni.baggit.ui.locations.model.ResponseItem
import com.nandohusni.baggit.utils.GPSTracker
import com.nandohusni.baggit.utils.SessionManager
import kotlinx.android.synthetic.main.location_fragment.*
import java.lang.IllegalArgumentException
import java.lang.IndexOutOfBoundsException
import java.util.*
import android.support.design.widget.BottomSheetBehavior
import com.nandohusni.baggit.ui.category.KategoryActivity
import com.nandohusni.baggit.ui.locations.LocationActivity
import com.nandohusni.baggit.ui.locations.model_currentlocations.ResultsItem
import kotlinx.android.synthetic.main.bottom_sheet.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.startActivity


class LocationFragment : Fragment() {
    private var googleMap: GoogleMap? = null
    private var lat: Double? = null
    private var lon: Double? = null
    private var sesi: SessionManager? = null

    private var mBottomSheetBehavior: BottomSheetBehavior<*>? = null
    private lateinit var viewModel: LocationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.location_fragment, container, false)
    }

    private fun gps() {
        val gps = GPSTracker(context)

        if (gps.canGetLocation()) {
            lat = gps.latitude
            lon = gps.longitude
            val name = convertName()
            searchLocations.setText(name.toString())
            setMarker()

        } else gps.showSettingGps()

    }


    private fun setMarker() {

        val sydney = lat?.let { lon?.let { it1 -> LatLng(it, it1) } }
        val cameraPosition = CameraPosition.Builder().target(sydney).zoom(12f).build()
        googleMap?.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

    }

    private fun convertName(): String? {


        var name: String? = null

        try {

            val geocoder = Geocoder(context, Locale.getDefault())
            val list = lat?.let { lon?.let { it1 -> geocoder.getFromLocation(it1, it, 2) } }

            name = list?.get(0)?.getAddressLine(0).toString()


        } catch (e: IndexOutOfBoundsException) {

        } catch (e: IllegalArgumentException) {

        }
        return name


    }


    @SuppressLint("MissingPermission")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBottomSheet()

        map.onCreate(savedInstanceState)
        val factory = activity?.application?.let {
            LocationViewModel.Factory(
                it
            )
        }


        viewModel = ViewModelProviders.of(this, factory).get(LocationViewModel::class.java)

        sesi?.idUser?.let { it ->
            viewModel.userLocation(it).observe(this, android.arch.lifecycle.Observer {

                bindMarker(it)
            })
        }






        sesi = context?.let { SessionManager(it) }
        locationswitch?.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {

                sesi?.token?.let { viewModel.allLocations(it) }?.observe(this, android.arch.lifecycle.Observer {

                    bindMarker(it)
                })

            } else {
                sesi?.idUser?.let { it ->
                    viewModel.userLocation(it).observe(this, android.arch.lifecycle.Observer {

                        bindMarker(it)
                    })
                }


            }
        }







        map.onResume() // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(activity!!.applicationContext)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        sesi?.token = "TOKEN"


        map.getMapAsync {

                mMap ->

            googleMap = mMap

            googleMap?.uiSettings?.isZoomControlsEnabled = true




            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(
                    arrayOf(
                        android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION
                    ), 1
                )


            } else {

                googleMap?.clear()
                gps()
                initButtonSheet()
                googleMap?.isMyLocationEnabled = true

            }


        }

    }

    private fun initButtonSheet() {


        val locations = "$lat,$lon"

        googleMap?.clear()

        LocationActivity.name?.let {
            viewModel.searchLocations(locations,getString(R.string.disct),it,false,getString(R.string.google_maps_key))
                .observe(this,android.arch.lifecycle.Observer {
                    showData(it)
                })
        }

        locShopping.setOnClickListener {
            googleMap?.clear()
            viewModel.searchLocations(locations,getString(R.string.disct),getString(R.string.mall),false,getString(R.string.google_maps_key))
                .observe(this,android.arch.lifecycle.Observer {
                    showData(it)
                })



        }
        locCoffe.setOnClickListener {

            googleMap?.clear()
            viewModel.searchLocations(locations,getString(R.string.disct),getString(R.string.food),false,getString(R.string.google_maps_key))
                .observe(this,android.arch.lifecycle.Observer {
                    showData(it)
                })


        }
        locWisate.setOnClickListener {

            googleMap?.clear()
            viewModel.searchLocations(locations,getString(R.string.disct),getString(R.string.zoo),false,getString(R.string.google_maps_key))
                .observe(this,android.arch.lifecycle.Observer {

                    showData(it)
                })


        }
        locOther.setOnClickListener {

            startActivity<KategoryActivity>()

        }

    }

    private fun showData(it: List<ResultsItem>?) {

        var glat : Double
        var glon : Double
        var name : String
        for (i in it?.indices!!){
            glat = it.get(i).geometry?.location?.lat ?: 0.0
            glon = it.get(i).geometry?.location?.lng ?: 0.0
            name = it.get(i).name.toString()

            setMarkerServer(glat,glon,name)
        }

    }

    private fun initBottomSheet() {

        mBottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet)
        mBottomSheetBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED

    }


    private fun bindMarker(it: List<ResponseItem>?) {

        var lat1: Double?
        var lon1: Double?
        var name: String?

        for (i in it?.indices!!) {

            lat1 = it[i].lat
            lon1 = it[i].jsonMemberLong
            name = it[i].description

            convertName()
            setMarkerServer(lat1, lon1, name)


        }


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1) {
            gps()
            initButtonSheet()
        }
    }

    private fun setMarkerServer(lat: Double?, lon: Double?, name: String?) {

        val sydney = lat?.let {
            lon?.let { it1 -> LatLng(it, it1) }
        }

        val cameraPosition = CameraPosition.Builder().target(sydney).zoom(12f).build()

        googleMap?.addMarker(sydney?.let {
            MarkerOptions().position(it).title(name)
        })

        val latbond = LatLngBounds.builder()
        latbond.include(sydney)

        googleMap?.animateCamera(CameraUpdateFactory.newLatLngBounds(latbond.build(), 12))

        googleMap?.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

    }

}
