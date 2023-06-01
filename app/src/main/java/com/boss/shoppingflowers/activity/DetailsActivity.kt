package com.boss.shoppingflowers.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.boss.shoppingflowers.R
import com.boss.shoppingflowers.databinding.ActivityDetailsBinding
import com.boss.shoppingflowers.databinding.ActivityMainBinding
import com.boss.shoppingflowers.model.Products
import com.bumptech.glide.Glide
import com.google.type.Color

class DetailsActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        initViews()
    }

    private fun initViews() {
        val products = intent.getSerializableExtra("item_center") as Products
        Log.d("ClassFound","Pro : $products")
        binding.apply {
            Glide.with(this@DetailsActivity).load(products.image).into(ivFlower)
            tvTitle.text = products.name
            ivBack.setOnClickListener {
                ivBack.setColorFilter(R.color.app_color_1)
                finish()
            }
        }
    }
}