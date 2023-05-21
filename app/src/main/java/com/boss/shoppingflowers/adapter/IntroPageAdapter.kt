package com.boss.shoppingflowers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.boss.shoppingflowers.R
import com.boss.shoppingflowers.model.IntroPageItem
import java.util.*
import kotlin.collections.ArrayList


class IntroPageAdapter(context: Context, private var items: ArrayList<IntroPageItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivPhoto: ImageView = view.findViewById(R.id.des_image)
        val description: TextView = view.findViewById(R.id.description)
        val body: TextView = view.findViewById(R.id.body)
        val bodyX: TextView = view.findViewById(R.id.bodyX)
        val bodyXL: TextView = view.findViewById(R.id.bodyXL)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_dot_indicator, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        if (holder is MyViewHolder) {
            holder.ivPhoto.setImageResource(item.img)
            holder.description.text = item.title
            holder.body.text = item.body
            holder.bodyX.text = item.bodyX
            holder.bodyXL.text = item.bodyXL
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}