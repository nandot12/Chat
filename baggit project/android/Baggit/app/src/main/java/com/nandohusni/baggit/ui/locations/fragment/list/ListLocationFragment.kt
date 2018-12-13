package com.nandohusni.baggit.ui.locations.fragment.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.nandohusni.baggit.R
import com.nandohusni.baggit.ui.locations.fragment.list.adapter.ListItemAdapter
import com.nandohusni.baggit.ui.locations.model.ResponseItem
import com.nandohusni.baggit.utils.SessionManager
import kotlinx.android.synthetic.main.list_fragment.*
import com.nandohusni.baggit.ui.locations.fragment.DetailListActivity
import com.nandohusni.baggit.utils.Constans
import org.jetbrains.anko.support.v4.startActivity


class ListLocationFragment : Fragment() {

    private var sesi: SessionManager? = null


    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sesi = context?.let { SessionManager(it) }

        val factory = activity?.application?.let {
            ListViewModel.Factory(
                it
            )
        }

        viewModel = ViewModelProviders.of(this, factory).get(ListViewModel::class.java)

        sesi?.idUser?.let { it ->
            viewModel.userLocation(it).observe(this, Observer {

                showData(it)
            })
        }
    }

    private fun showData(it: List<ResponseItem>?) {

        recyclerviewlist.layoutManager = LinearLayoutManager(context)
        recyclerviewlist.adapter = it?.let { it1 ->
            ListItemAdapter(it1,object  : ListItemAdapter.OnItemClickListener{
                override fun onItemClick(item: ResponseItem) {

                    startActivity<DetailListActivity>(Constans.LAT to item.lat,Constans.LON to item.jsonMemberLong,
                        Constans.NAME to item.description)

                }

            })
        }



    }

}
