package com.boss.shoppingflowers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.boss.shoppingflowers.R
import com.boss.shoppingflowers.model.Markets
import com.bumptech.glide.Glide

class HomeAdapterBottom(var context: Context, private var items: ArrayList<Markets>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_home_bottom_layout, parent, false)
        return HomeBottomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        if (holder is HomeBottomViewHolder) {
            val tv_title = holder.tv_title
            val tv_number = holder.tv_number
            val tv_count = holder.tv_count
            val tv_desc = holder.tv_desc
            val iv_body = holder.iv_body
            tv_title.text = item.title
            tv_number.text = item.number
            tv_count.text = item.count
            tv_desc.text = item.desc
            Glide.with(context).load(item.body).into(iv_body)
        }
    }

    class HomeBottomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv_title = view.findViewById<TextView>(R.id.tv_title)
        val tv_number = view.findViewById<TextView>(R.id.tv_number)
        val tv_desc = view.findViewById<TextView>(R.id.tv_desc)
        val tv_count = view.findViewById<TextView>(R.id.tv_count)
        val iv_body = view.findViewById<ImageView>(R.id.iv_body)
    }
}