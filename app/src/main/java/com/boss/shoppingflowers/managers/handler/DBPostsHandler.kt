package com.boss.shoppingflowers.managers.handler

import com.boss.shoppingflowers.model.Products

interface DBPostsHandler {
    fun onSuccess(posts: ArrayList<Products>)
    fun onError(e: Exception)
}