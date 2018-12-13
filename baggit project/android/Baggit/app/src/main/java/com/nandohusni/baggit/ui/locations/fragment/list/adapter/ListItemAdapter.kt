package com.nandohusni.baggit.ui.locations.fragment.list.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nandohusni.baggit.R
import com.nandohusni.baggit.ui.locations.model.ResponseItem
import kotlinx.android.synthetic.main.itemlocations.view.*

class ListItemAdapter(val data: List<ResponseItem>, private var listener: OnItemClickListener) :
    RecyclerView.Adapter<ListItemAdapter.MyHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: ResponseItem)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) =
        MyHolder(LayoutInflater.from(p0.context).inflate(R.layout.itemlocations, p0, false))


    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(p0: ListItemAdapter.MyHolder, p1: Int) {
        p0.bind(data[p1], listener)
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            responseItem: ResponseItem,
            listener: OnItemClickListener?
        ) {
            itemView.textName.text = responseItem.description
            itemView.setOnClickListener {
                listener?.onItemClick(responseItem)
            }

        }

    }
}