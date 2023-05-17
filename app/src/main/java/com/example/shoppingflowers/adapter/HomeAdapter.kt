package com.example.shoppingflowers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shoppingflowers.R
import com.example.shoppingflowers.modeltest.Member

class HomeAdapter(var context: Context, private var items: ArrayList<Member>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_home_layout, parent, false)
        return HomeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        if (holder is HomeViewHolder) {
            val tv_center = holder.tvCenter
            val iv_center = holder.ivCenter
            tv_center!!.text = item.name
            Glide.with(context).load(item.image).into(iv_center)
        }
    }

    class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCenter = view.findViewById<TextView>(R.id.tv_center)
        val ivCenter = view.findViewById<ImageView>(R.id.iv_center)
    }
}