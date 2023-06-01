package com.boss.shoppingflowers.utils

import android.content.res.Resources
import com.boss.shoppingflowers.ProductApplication
import com.boss.shoppingflowers.R
import com.boss.shoppingflowers.modeltest.Member

class DataEntry {
    companion object {
        fun prepareMemerList(): ArrayList<Member> {
            val members: java.util.ArrayList<Member> = java.util.ArrayList()
            members.add(
                Member(
                    "https://ae04.alicdn.com/kf/Sb561ad725978458b97d7a35c9f7538f1X.jpg_640x640.jpg",
                    ProductApplication.context?.resources?.getString(R.string.str_flowers)
                )
            )
            members.add(
                Member(
                    "https://ae04.alicdn.com/kf/Sef4c51849fef4c4fb79976ba891713b0K.jpg_640x640.jpg",
                    ProductApplication.context?.resources?.getString(R.string.cakes)
                )
            )
            members.add(
                Member(
                    "https://media.istockphoto.com/id/173255460/photo/assortment-of-fruits.jpg?b=1&s=170667a&w=0&k=20&c=DTUxwA3VoqtIwRHy9mwFi8vFeMlPtrwULj8KJkeiwlE=",
                    ProductApplication.context?.resources?.getString(R.string.fruits)
                )
            )
            return members
        }
    }
}