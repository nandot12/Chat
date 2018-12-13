package com.nandohusni.baggit.ui.addLokasi.ui.addlocation

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nandohusni.baggit.R
import kotlinx.android.synthetic.main.add_location_fragment.*
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.location.places.ui.PlaceAutocomplete
import android.content.Intent
import com.nandohusni.baggit.ui.addLokasi.model.Response
import com.nandohusni.baggit.ui.locations.LocationActivity
import com.nandohusni.baggit.utils.SessionManager
import org.jetbrains.anko.support.v4.startActivity


class AddLocationFragment : Fragment() {

    private var lat: Double? = null
    private var lon: Double? = null
    private var name: String? = null

    companion object {
        fun newInstance() = AddLocationFragment()
    }

    private lateinit var viewModel: AddLocationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.add_location_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory = activity?.application?.let {
            AddLocationViewModel.Factory(
                it
            )
        }
        viewModel = ViewModelProviders.of(this, factory).get(AddLocationViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btnAddLocations.setOnClickListener { _ ->

            lat?.let { it1 ->
                lon?.let { it2 ->
                    context?.let { it3 ->
                        SessionManager(it3).idUser?.let { it4 ->
                            viewModel.addLocation(it1, it2, addNameLocations.text.toString(), it4).observe(this,
                                Observer {

                                    showData(it)
                                })
                        }
                    }
                }
            }
        }

        addNameLocations.setOnClickListener {
            showName(1)
        }
    }

    private fun showData(it: Response?) {

        if (it?.result?.ok == 1) startActivity<LocationActivity>()

    }

    private fun showName(i: Int) {
        try {
            val intent = PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                .build(activity)
            startActivityForResult(intent, i)
        } catch (e: GooglePlayServicesRepairableException) {
            // TODO: Handle the error.
        } catch (e: GooglePlayServicesNotAvailableException) {
            // TODO: Handle the error.
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {
            val datas = PlaceAutocomplete.getPlace(activity, data)
            lat = datas.latLng.latitude
            lon = datas.latLng.longitude
            name = datas.address.toString()
            addNameLocations.text = name

        }
    }


}
