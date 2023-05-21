package com.boss.shoppingflowers.managers

import android.annotation.SuppressLint
import com.boss.shoppingflowers.managers.handler.DBMarketHandler
import com.boss.shoppingflowers.managers.handler.DBMarketsHandler
import com.boss.shoppingflowers.managers.handler.DBPostHandler
import com.boss.shoppingflowers.managers.handler.DBPostsHandler
import com.boss.shoppingflowers.model.Markets
import com.boss.shoppingflowers.model.Products
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Locale

private var POST_PATH = "products"
private var MARKETS_PATH = "markets"

object DatabaseManager {

    @SuppressLint("StaticFieldLeak")
    private var database = FirebaseFirestore.getInstance()

    fun storePosts(post: Products, handler: DBPostHandler) {
        val reference = database.collection(POST_PATH)
        reference.document(post.id).set(post).addOnSuccessListener {
            handler.onSuccess(post)
        }.addOnFailureListener {
            handler.onError(it)
        }
    }

    fun storeMarkets(post: Markets, handler: DBMarketHandler) {
        val reference = database.collection(MARKETS_PATH)
        reference.document(post.id).set(post).addOnSuccessListener {
            handler.onSuccess(post)
        }.addOnFailureListener {
            handler.onError(it)
        }
    }

    fun loadFeeds(handler: DBPostsHandler) {
        val reference = database.collection(POST_PATH)
        reference.get().addOnCompleteListener {
            val posts = ArrayList<Products>()
            if (it.isSuccessful) {
                for (document in it.result!!) {
                    val id = document.getString("id")
                    val desc = document.getString("desc")
                    val name = document.getString("name")
                    val sum = document.getString("sum")
                    val image = document.getString("image")
                    val type = document.getString("type")

                    val post = Products(id!!, image!!, name!!, desc!!, sum!!, type!!)
                    post.id = id
                    post.name = name
                    post.image = image
                    post.desc = desc
                    post.sum = sum
                    post.type = type
                    posts.add(post)
                }
                handler.onSuccess(posts)
            } else {
                handler.onError(it.exception!!)
            }
        }
    }

    fun loadFeeds(handler: DBMarketsHandler) {
        val reference = database.collection(MARKETS_PATH)
        reference.get().addOnCompleteListener {
            val posts = ArrayList<Markets>()
            if (it.isSuccessful) {
                for (document in it.result!!) {
                    val id = document.getString("id")
                    val title = document.getString("title")
                    val number = document.getString("number")
                    val count = document.getString("count")
                    val desc = document.getString("desc")
                    val body = document.getString("body")

                    val post = Markets(id!!, title!!, number!!, count!!, desc!!, body!!)
                    post.id = id
                    post.title = title
                    post.number = number
                    post.count = count
                    post.desc = desc
                    post.body = body
                    posts.add(post)
                }
                handler.onSuccess(posts)
            } else {
                handler.onError(it.exception!!)
            }
        }
    }

    fun loadFeeds(where: String, handler: DBPostsHandler) {
        val reference = database.collection(POST_PATH)
        reference.whereEqualTo("type", where).get().addOnCompleteListener {
            val posts = ArrayList<Products>()
            if (it.isSuccessful) {
                for (document in it.result!!) {
                    val id = document.getString("id")
                    val desc = document.getString("desc")
                    val name = document.getString("name")
                    val sum = document.getString("sum")
                    val image = document.getString("image")
                    val type = document.getString("type")

                    val post = Products(id!!, image!!, name!!, desc!!, sum!!, type!!)
                    post.id = id
                    post.name = name
                    post.image = image
                    post.desc = desc
                    post.sum = sum
                    post.type = type
                    posts.add(post)
                }
                handler.onSuccess(posts)
            } else {
                handler.onError(it.exception!!)
            }
        }
    }

    fun searchFeeds(where: String, handler: DBPostsHandler) {
        val reference = database.collection(POST_PATH)
//        val q = reference.orderBy("name").startAt(where.trim().uppercase()).endAt(where.trim().lowercase() + "\uf8ff")
        val q = reference.orderBy("name").startAt(where.trim().uppercase(Locale.getDefault())).endAt(where.trim() + "\uf8ff")
        q.get().addOnCompleteListener {
            val posts = ArrayList<Products>()
            if (it.isSuccessful) {
                for (document in it.result!!) {
                    val id = document.getString("id")
                    val desc = document.getString("desc")
                    val name = document.getString("name")
                    val sum = document.getString("sum")
                    val image = document.getString("image")
                    val type = document.getString("type")

                    val post = Products(id!!, image!!, name!!, desc!!, sum!!, type!!)
                    post.id = id
                    post.name = name
                    post.image = image
                    post.desc = desc
                    post.sum = sum
                    post.type = type
                    posts.add(post)
                }
                handler.onSuccess(posts)
            } else {
                handler.onError(it.exception!!)
            }
        }
    }

    fun getAllProduct(handler: DBPostsHandler) {
        val reference = database.collection(POST_PATH)
        reference.get().addOnCompleteListener {
            val posts = ArrayList<Products>()
            if (it.isSuccessful) {
                for (document in it.result!!) {
                    val id = document.getString("id")
                    val desc = document.getString("desc")
                    val name = document.getString("name")
                    val sum = document.getString("sum")
                    val image = document.getString("image")
                    val type = document.getString("type")

                    val post = Products(id!!, image!!, name!!, desc!!, sum!!, type!!)
                    post.id = id
                    post.name = name
                    post.image = image
                    post.desc = desc
                    post.sum = sum
                    post.type = type
                    posts.add(post)
                }
                handler.onSuccess(posts)
            } else {
                handler.onError(it.exception!!)
            }
        }
    }
}
