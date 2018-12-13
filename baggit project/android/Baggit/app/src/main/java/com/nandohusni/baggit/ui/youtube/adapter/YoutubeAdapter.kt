package com.nandohusni.baggit.ui.youtube.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nandohusni.baggit.R
import com.nandohusni.baggit.ui.detailyoutube.FullscreenDemoActivity
import com.nandohusni.baggit.ui.youtube.model.ItemsItem
import com.nandohusni.baggit.utils.Constans
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.itemyoutube.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class YoutubeAdapter(val data: List<ItemsItem>) : RecyclerView.Adapter<YoutubeAdapter.MyHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyHolder {
        return MyHolder(LayoutInflater.from(p0.context).inflate(R.layout.itemyoutube, p0, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(p0: MyHolder, p1: Int) {
        p0.bind(data.get(p1))
        p0.itemView.setOnClickListener {

            p0.itemView.setOnClickListener {



                p0.itemView.context.startActivity<FullscreenDemoActivity>(Constans.ID to data.get(p1).id?.videoId.toString())
            }
        }
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(get: ItemsItem) {

            Picasso.get().load(get.snippet?.thumbnails?.high?.url).into(itemView.itemImage)
            itemView.itemSubtitle.text = get.snippet?.description
            itemView.itemTitle.text = get.snippet?.title

        }

    }
}