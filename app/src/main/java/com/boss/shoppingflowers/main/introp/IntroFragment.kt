package com.boss.shoppingflowers.main.introp

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.boss.shoppingflowers.R
import com.boss.shoppingflowers.databinding.FragmentIntroBinding
import com.boss.shoppingflowers.extencion.activityNavController
import com.boss.shoppingflowers.extencion.navigateSafely
import com.boss.shoppingflowers.managers.AuthManager
import com.boss.shoppingflowers.managers.DatabaseManager
import com.boss.shoppingflowers.managers.handler.DBMarketHandler
import com.boss.shoppingflowers.managers.handler.DBPostHandler
import com.boss.shoppingflowers.model.IntroPageItem
import com.boss.shoppingflowers.model.Markets
import com.boss.shoppingflowers.model.Products
import com.google.android.material.tabs.TabLayoutMediator

class IntroFragment : Fragment(R.layout.fragment_intro) {
    var array: ArrayList<IntroPageItem> = ArrayList()
    private val binding by viewBinding(FragmentIntroBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.setAdapter(
            com.boss.shoppingflowers.adapter.IntroPageAdapter(
                requireContext(),
                getItems()
            )
        )
        initViews()
    }

    private fun initViews() {
        binding.apply {

            TabLayoutMediator(tabs, viewPager) { tab, position ->
//                tab.text = array[position].toString()
            }.attach()
            next.setOnClickListener {
                vibrate()
                viewPager.currentItem = ++viewPager.currentItem
            }
            prev.setOnClickListener {
                vibrate()
                viewPager.currentItem = --viewPager.currentItem
            }
            tvSkip.setOnClickListener {
                vibrate()
                viewPager.currentItem = getItems().size - 1
            }
        }
        applyPageStateChanges()
        binding.getStart.setOnClickListener {
            AuthManager.isAuthorized = 3
            activityNavController().navigateSafely(R.id.action_global_mainFlowFragment)
        }

//        prepareDefaultProductCenter()
//        prepareDefaultProductBottom()
    }

    private fun applyPageStateChanges() {
        binding.apply {
            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int,
                ) {
                    if (position == getItems().size - 1) {
                        next.visibility = View.GONE
                        getStart.visibility = View.VISIBLE
                    } else {
                        next.visibility = View.VISIBLE
                        getStart.visibility = View.GONE
                    }
                    if (position == 1) {
                        prev.visibility = View.VISIBLE
                    } else {
                        prev.visibility = View.GONE
                    }
                }
            })
        }
    }

    private fun getItems(): ArrayList<IntroPageItem> {
        val items = ArrayList<IntroPageItem>()

        items.add(
            IntroPageItem(
                R.drawable.rose,
                resources.getString(R.string.str_indicator_intro_desc),
                resources.getString(R.string.str_indicator_intro_body),
                resources.getString(R.string.str_indicator_intro_bodyX),
                resources.getString(R.string.str_indicator_intro_bodyXL)
            )
        )
        items.add(
            IntroPageItem(
                R.drawable.bouquet,
                resources.getString(R.string.str_indicator_intro_desc2),
                resources.getString(R.string.str_indicator_intro_body),
                resources.getString(R.string.str_indicator_intro_bodyXL2),
                resources.getString(R.string.str_indicator_intro_bodyXL)
            )
        )
        items.add(
            IntroPageItem(
                R.drawable.pngegg,
                resources.getString(R.string.str_indicator_intro_desc3),
                resources.getString(R.string.str_indicator_intro_body),
                resources.getString(R.string.str_indicator_intro_bodyX3),
                resources.getString(R.string.str_indicator_intro_bodyX)
            )
        )
        return items
    }

    fun vibrate() {
        val vibrator = requireActivity().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(100)
        }
    }


//    private fun prepareDefaultProductCenter() {
//        val members: java.util.ArrayList<Products> = java.util.ArrayList()
//        members.add(
//            Products(
//                "1",
//                "https://ae04.alicdn.com/kf/Sb561ad725978458b97d7a35c9f7538f1X.jpg_640x640.jpg",
//                "Gullar",
//                "uzunligi 50sm",
//                "134 ming so'm",
//                com.boss.shoppingflowers.enums.Type.FLOWERS.toString()
//            )
//        )
//        members.add(
//            Products(
//                "2",
//                "https://ae04.alicdn.com/kf/Sad59ba71023042348948ef4336ae399ee.jpg_640x640.jpg",
//                "To'rt",
//                "uzunligi 60sm",
//                "145 ming so'm",
//                com.boss.shoppingflowers.enums.Type.CAKES.toString()
//            )
//        )
//        members.add(
//            Products(
//                "3",
//                "https://ae04.alicdn.com/kf/S22fb60e2e67f4cdeacc93103e367894af.jpg_640x640.jpg",
//                "Gullar",
//                "uzunligi 70sm",
//                "167 ming so'm",
//                com.boss.shoppingflowers.enums.Type.FLOWERS.toString()
//            )
//        )
//        members.add(
//            Products(
//                "4",
//                "https://ae04.alicdn.com/kf/Heab259f697a34ffcbb9f18ac726cb4a38.jpg_640x640.jpg",
//                "Meva",
//                "uzunligi 80sm",
//                "190 ming so'm",
//                com.boss.shoppingflowers.enums.Type.FRUITS.toString()
//            )
//        )
//        members.add(
//            Products(
//                "5",
//                "https://images.unsplash.com/photo-1534531173927-aeb928d54385?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80",
//                "Meva",
//                "uzunligi 10sm",
//                "90 ming so'm",
//                com.boss.shoppingflowers.enums.Type.FRUITS.toString()
//            )
//        )
//        members.add(
//            Products(
//                "6",
//                "https://images.unsplash.com/photo-1490885578174-acda8905c2c6?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2069&q=80",
//                "Meva",
//                "uzunligi 10sm",
//                "290 ming so'm",
//                com.boss.shoppingflowers.enums.Type.FRUITS.toString()
//            )
//        )
//        members.add(
//            Products(
//                "7",
//                "https://images.unsplash.com/photo-1526318472351-c75fcf070305?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=387&q=80",
//                "Meva",
//                "uzunligi 20sm",
//                "230 ming so'm",
//                com.boss.shoppingflowers.enums.Type.FRUITS.toString()
//            )
//        )
//
//        members.add(
//            Products(
//                "8",
//                "https://images.unsplash.com/photo-1460039230329-eb070fc6c77c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=435&q=80",
//                "Gullar",
//                "uzunligi 60sm",
//                "654 ming so'm",
//                com.boss.shoppingflowers.enums.Type.FLOWERS.toString()
//            )
//        )
//        members.add(
//            Products(
//                "9",
//                "https://images.unsplash.com/photo-1613539246066-78db6ec4ff0f?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=386&q=80",
//                "Gullar",
//                "uzunligi 70sm",
//                "432 ming so'm",
//                com.boss.shoppingflowers.enums.Type.FLOWERS.toString()
//            )
//        )
//        members.add(
//            Products(
//                "10",
//                "https://images.unsplash.com/photo-1578985545062-69928b1d9587?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=789&q=80",
//                "To'rt",
//                "uzunligi 50sm",
//                "215 ming so'm",
//                com.boss.shoppingflowers.enums.Type.CAKES.toString()
//            )
//        )
//        members.add(
//            Products(
//                "11",
//                "https://images.unsplash.com/photo-1558301211-0d8c8ddee6ec?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=336&q=80",
//                "To'rt",
//                "uzunligi 80sm",
//                "176 ming so'm",
//                com.boss.shoppingflowers.enums.Type.CAKES.toString()
//            )
//        )
//        members.add(
//            Products(
//                "12",
//                "https://images.unsplash.com/photo-1606890737304-57a1ca8a5b62?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2003&q=80",
//                "To'rt",
//                "uzunligi 75sm",
//                "187 ming so'm",
//                com.boss.shoppingflowers.enums.Type.CAKES.toString()
//            )
//        )
//        createToDB(members)
//    }
//
//    private fun prepareDefaultProductBottom() {
//        val members: java.util.ArrayList<Markets> = java.util.ArrayList()
//        members.add(
//            Markets(
//                "1",
//                "44 Sodiq Azimov ko'chasi",
//                "tel: 8 93 509 97 98",
//                "34",
//                "open &  close 00:00",
//                "https://ae04.alicdn.com/kf/Sb561ad725978458b97d7a35c9f7538f1X.jpg_640x640.jpg"
//            )
//        )
//        members.add(
//            Markets(
//                "2",
//                "54 Alisher Navoiy gullar",
//                "tel: 8 99 509 39 19",
//                "18",
//                "open &  close 22:00",
//                "https://ae04.alicdn.com/kf/Sad59ba71023042348948ef4336ae399ee.jpg_640x640.jpg"
//            )
//        )
//        members.add(
//            Markets(
//                "3",
//                "34 Farg'na makro 64-uy",
//                "tel: 8 99 504 12 14",
//                "76",
//                "open &  close 18:00",
//                "https://ae04.alicdn.com/kf/S22fb60e2e67f4cdeacc93103e367894af.jpg_640x640.jpg"
//            )
//        )
//        members.add(
//            Markets(
//                "4",
//                "18 uy Sirg`ali gullar",
//                "tel: 8 97 678 04 24",
//                "32",
//                "open &  close 19:00",
//                "https://ae04.alicdn.com/kf/Heab259f697a34ffcbb9f18ac726cb4a38.jpg_640x640.jpg"
//            )
//        )
//        createToDB2(members)
//    }
//
//    private fun createToDB(members2: ArrayList<Products>) {
//        for (i in members2) {
//            DatabaseManager.storePosts(i, object : DBPostHandler {
//                override fun onSuccess(post: Products) {
//
//                }
//
//                override fun onError(e: Exception) {
//
//                }
//            })
//        }
//    }
//
//    private fun createToDB2(members2: ArrayList<Markets>) {
//        for (i in members2) {
//            DatabaseManager.storeMarkets(i, object : DBMarketHandler {
//                override fun onSuccess(post: Markets) {
//
//                }
//
//                override fun onError(e: Exception) {
//
//                }
//            })
//        }
//    }
}