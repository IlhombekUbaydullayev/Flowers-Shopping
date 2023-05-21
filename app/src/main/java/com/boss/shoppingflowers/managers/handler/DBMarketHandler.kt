package com.boss.shoppingflowers.managers.handler

import com.boss.shoppingflowers.model.Markets
import com.boss.shoppingflowers.model.Products


interface DBMarketHandler {
    fun onSuccess(post: Markets)
    fun onError(e: Exception)
}