package com.boss.shoppingflowers.managers.handler

import com.boss.shoppingflowers.model.Products


interface DBPostHandler {
    fun onSuccess(post: Products)
    fun onError(e: Exception)
}