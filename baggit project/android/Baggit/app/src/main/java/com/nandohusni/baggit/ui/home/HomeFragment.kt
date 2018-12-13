package com.nandohusni.baggit.ui.home

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.nandohusni.baggit.R
import com.nandohusni.baggit.ui.locations.LocationActivity
import com.nandohusni.baggit.ui.locations.MapsActivity
import com.nandohusni.baggit.ui.schedule.ScheduleActivity
import com.nandohusni.baggit.ui.statistic.StatisticActivity
import com.nandohusni.baggit.ui.youtube.YoutubeActivity
import kotlinx.android.synthetic.main.home_fragment.*
import org.jetbrains.anko.support.v4.startActivity

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnEarch.setOnClickListener {

            startActivity<YoutubeActivity>()
        }

        btnStatistic.setOnClickListener {

            startActivity<StatisticActivity>()
        }

        homeLocations.setOnClickListener {
            startActivity<LocationActivity>()
        }

        btnSchedule.setOnClickListener {
            startActivity<ScheduleActivity>()
        }


    }

}
