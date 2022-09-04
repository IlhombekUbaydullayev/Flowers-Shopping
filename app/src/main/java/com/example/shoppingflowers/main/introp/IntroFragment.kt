package com.example.shoppingflowers.main.introp

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.shoppingflowers.R
import com.example.shoppingflowers.adapter.IntroPageAdapter
import com.example.shoppingflowers.databinding.FragmentIntroBinding
import com.example.shoppingflowers.extencion.activityNavController
import com.example.shoppingflowers.extencion.navigateSafely
import com.example.shoppingflowers.manager.AuthManager
import com.example.shoppingflowers.model.IntroPageItem
import com.google.android.material.tabs.TabLayoutMediator

class IntroFragment : Fragment(R.layout.fragment_intro) {
    var array : ArrayList<IntroPageItem> = ArrayList()
    private val binding by viewBinding(FragmentIntroBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.setAdapter(IntroPageAdapter(requireContext(),getItems()))
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
    }

    private fun applyPageStateChanges() {
        binding.apply {
            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    if (position == getItems().size - 1) {
                        next.visibility = View.GONE
                        getStart.visibility = View.VISIBLE
                    } else {
                        next.visibility = View.VISIBLE
                        getStart.visibility = View.GONE
                    }
                    if (position == 1){
                        prev.visibility = View.VISIBLE
                    }else {
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
                "Pushti atirgullar",
                "onlayn shopping market orqali",
                "o'zingizga yoqqan pushti",
                "atirgullarni xarid qiling!"
            )
        )
        items.add(
            IntroPageItem(
                R.drawable.bouquet,
                "Lilya gullari",
                "onlayn shopping market orqali",
                "o'zingizga yoqqan lilya",
                "gullarini xarid qiling!"
            )
        )
        items.add(
            IntroPageItem(
                R.drawable.pngegg,
                "Gullar bozori",
                "onlayn shopping market orqali",
                "o'zingizga yoqqan turli xil ",
                "gullarni xarid qiling"
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
}