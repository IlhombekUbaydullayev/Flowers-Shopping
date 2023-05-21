package com.boss.shoppingflowers.managers.handler

import com.boss.shoppingflowers.model.Markets
import com.boss.shoppingflowers.model.Products


interface DBMarketsHandler {
    fun onSuccess(post: ArrayList<Markets>)
    fun onError(e: Exception)
}