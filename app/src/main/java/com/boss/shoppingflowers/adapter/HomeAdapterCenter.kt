package com.boss.shoppingflowers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.boss.shoppingflowers.R
import com.boss.shoppingflowers.model.Products
import com.bumptech.glide.Glide

class HomeAdapterCenter(var context: Context, private var items: MutableList<Products>,
                        private val clickListener: (Products) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_home_center_layout, parent, false)
        return HomeCenterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        if (holder is HomeCenterViewHolder) {
            val tv_center = holder.tvCenter
            val iv_center = holder.ivCenter
            val tv_desc = holder.tv_desc
            val tv_sum = holder.tv_sum
            val ll_to_details = holder.ll_to_details
            tv_center!!.text = item.name
            tv_desc!!.text = item.desc
            tv_sum!!.text = item.sum
            Glide.with(context).load(item.image).into(iv_center)
            ll_to_details.setOnClickListener {
                clickListener(item)
            }
        }
    }

    class HomeCenterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCenter = view.findViewById<TextView>(R.id.tv_center)
        val ivCenter = view.findViewById<ImageView>(R.id.iv_center)
        val tv_desc = view.findViewById<TextView>(R.id.tv_desc)
        val tv_sum = view.findViewById<TextView>(R.id.tv_sum)
        val ll_to_details = view.findViewById<LinearLayout>(R.id.ll_to_details)
    }
}