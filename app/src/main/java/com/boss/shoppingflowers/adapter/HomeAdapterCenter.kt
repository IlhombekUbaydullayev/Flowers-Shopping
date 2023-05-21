package com.boss.shoppingflowers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.boss.shoppingflowers.R
import com.boss.shoppingflowers.model.Products
import com.bumptech.glide.Glide

class HomeAdapterCenter(var context: Context, private var items: ArrayList<Products>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_home_center_layout, parent, false)
        return com.boss.shoppingflowers.adapter.HomeAdapterCenter.HomeCenterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        if (holder is com.boss.shoppingflowers.adapter.HomeAdapterCenter.HomeCenterViewHolder) {
            val tv_center = holder.tvCenter
            val iv_center = holder.ivCenter
            val tv_desc = holder.tv_desc
            val tv_sum = holder.tv_sum
            tv_center!!.text = item.name
            tv_desc!!.text = item.desc
            tv_sum!!.text = item.sum
            Glide.with(context).load(item.image).into(iv_center)
        }
    }

    class HomeCenterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCenter = view.findViewById<TextView>(R.id.tv_center)
        val ivCenter = view.findViewById<ImageView>(R.id.iv_center)
        val tv_desc = view.findViewById<TextView>(R.id.tv_desc)
        val tv_sum = view.findViewById<TextView>(R.id.tv_sum)
    }
}