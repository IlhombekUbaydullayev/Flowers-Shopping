package com.boss.shoppingflowers.model

import java.io.Serializable

data class Products(
    var id: String? = null,
    var image: String? = null,
    var name: String? = null,
    var desc: String? = null,
    var sum: String? = null,
    var type: String? = null
) : Serializable