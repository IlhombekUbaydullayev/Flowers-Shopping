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
import com.example.shoppingflowers.modeltest.MemberCenter

class SearchAdapter(var context: Context, private var items: ArrayList<MemberCenter>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_search_layout, parent, false)
        return SearchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        if (holder is SearchViewHolder) {
            val tv_center = holder.tvCenter
            val iv_center = holder.ivCenter
            tv_center!!.text = item.name
            Glide.with(context).load(item.image).into(iv_center)
        }
    }

    class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCenter = view.findViewById<TextView>(R.id.tv_center)
        val ivCenter = view.findViewById<ImageView>(R.id.iv_center)
    }
}