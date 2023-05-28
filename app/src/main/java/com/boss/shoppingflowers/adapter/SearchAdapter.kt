package com.boss.shoppingflowers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.boss.shoppingflowers.databinding.SearchDataBinding
import com.boss.shoppingflowers.model.Products
import com.bumptech.glide.Glide

class SearchAdapter(var context : Context): PagingDataAdapter<Products, SearchAdapter.SearchViewHolder>(Companion){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val dataBinding = SearchDataBinding.inflate(
            layoutInflater,
            parent,
            false)
        return SearchViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val product = getItem(position) ?: return
        holder.bindProduct(product)
        Glide.with(context).load(product.image).into(holder.image)
    }

    companion object : DiffUtil.ItemCallback<Products>() {
        override fun areItemsTheSame(oldItem: Products, newItem: Products): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Products, newItem: Products): Boolean {
            return oldItem == newItem
        }
    }

    inner class SearchViewHolder(private val dataBinding: SearchDataBinding) : RecyclerView.ViewHolder(dataBinding.root) {
        fun bindProduct(products: Products) {
            dataBinding.products = products
        }
        val image = dataBinding.ivCenter
    }
}