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
import com.boss.shoppingflowers.main.home.HomeFragment
import com.boss.shoppingflowers.modeltest.Member
import com.bumptech.glide.Glide

class HomeAdapter(var context: Context, var fragment : HomeFragment, private var items: ArrayList<Member>) :
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
            val ll_parent = holder.ll_parent
            tv_center!!.text = item.name
            Glide.with(context).load(item.image).into(iv_center)

            ll_parent.setOnClickListener {
                fragment.onClick(position)
            }
        }
    }

    class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCenter = view.findViewById<TextView>(R.id.tv_center)
        val ivCenter = view.findViewById<ImageView>(R.id.iv_center)
        val ll_parent = view.findViewById<LinearLayout>(R.id.ll_parent)
    }
}