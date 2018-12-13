package com.nandohusni.baggit.ui.youtube

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nandohusni.baggit.R
import com.nandohusni.baggit.ui.signup.SignUpViewModel
import com.nandohusni.baggit.ui.youtube.adapter.YoutubeAdapter
import kotlinx.android.synthetic.main.youtube_fragment.*
import org.jetbrains.anko.support.v4.toast

class YoutubeFragment : Fragment() {

    companion object {
        fun newInstance() = YoutubeFragment()

    }
    private var adapter : YoutubeAdapter? = null

    private lateinit var viewModel: YoutubeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.youtube_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = activity?.application?.let {
            YoutubeViewModel.Factory(
                it
            )
        }
        viewModel = ViewModelProviders.of(this, factory).get(YoutubeViewModel::class.java)


        viewModel.youtube().observe(this, Observer {

            t ->

            if(t != null) {
                adapter = YoutubeAdapter(t)
                choose()
            }
            else toast("nothing")

        })


    }

    private fun choose() {
        recyclerview.adapter  = adapter
        recyclerview.layoutManager  = GridLayoutManager(context,2)
    }

}
