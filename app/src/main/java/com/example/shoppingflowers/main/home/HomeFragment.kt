package com.example.shoppingflowers.main.home

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.shoppingflowers.BaseFragment
import com.example.shoppingflowers.R
import com.example.shoppingflowers.adapter.HomeAdapter
import com.example.shoppingflowers.adapter.HomeAdapterBottom
import com.example.shoppingflowers.adapter.HomeAdapterCenter
import com.example.shoppingflowers.databinding.FragmentHomeBinding
import com.example.shoppingflowers.modeltest.Member
import com.example.shoppingflowers.modeltest.MemberCenter
import java.util.Locale

class HomeFragment: BaseFragment(R.layout.fragment_home) {
    private var context: Context? = null
    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadLocale()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        val members = prepareMemerList()
        refreshAdapter(members)
        val members2 = prepareMemerListCenter()
        refreshAdapterCenter(members2)
        val members3 = prepareMemerListBottom()
        refreshAdapterBottom(members2)

    }


    private fun initViews() {
        binding.apply {
            llV1.setOnClickListener {
                tvFlower.setTextColor(Color.RED)
                tvFlower2.setTextColor(Color.DKGRAY)
                tvFlower3.setTextColor(Color.DKGRAY)
            }
            llV2.setOnClickListener {
                tvFlower.setTextColor(Color.DKGRAY)
                tvFlower2.setTextColor(Color.RED)
                tvFlower3.setTextColor(Color.DKGRAY)
            }
            llV3.setOnClickListener {
                tvFlower.setTextColor(Color.DKGRAY)
                tvFlower2.setTextColor(Color.DKGRAY)
                tvFlower3.setTextColor(Color.RED)
            }
            ivLanguages.setOnClickListener {
                showChangeDialogLanguage()
            }
        }

        binding.rvHome.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvHome2.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvHome3.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun refreshAdapter(members: ArrayList<Member>) {
        val adapter = HomeAdapter(requireContext(), members)
        binding.rvHome.adapter = adapter
    }

    private fun prepareMemerList(): ArrayList<Member> {
        val members : java.util.ArrayList<Member> = java.util.ArrayList()
        members.add(Member("https://ae04.alicdn.com/kf/Sb561ad725978458b97d7a35c9f7538f1X.jpg_640x640.jpg", "Gullar"))
        members.add(Member("https://ae04.alicdn.com/kf/Sad59ba71023042348948ef4336ae399ee.jpg_640x640.jpg", "To'rt"))
        members.add(Member("https://ae04.alicdn.com/kf/S22fb60e2e67f4cdeacc93103e367894af.jpg_640x640.jpg", "Gullar"))
        members.add(Member("https://ae04.alicdn.com/kf/Heab259f697a34ffcbb9f18ac726cb4a38.jpg_640x640.jpg", "Meva"))
        members.add(Member("https://ae04.alicdn.com/kf/Sc61342f6892447e388e5cc74b06adbf1t.jpg_640x640.jpg", "Gullar"))
        members.add(Member("https://ae04.alicdn.com/kf/Sef4c51849fef4c4fb79976ba891713b0K.jpg_640x640.jpg", "To'rt"))
        members.add(Member("https://media.istockphoto.com/id/173255460/photo/assortment-of-fruits.jpg?b=1&s=170667a&w=0&k=20&c=DTUxwA3VoqtIwRHy9mwFi8vFeMlPtrwULj8KJkeiwlE=", "Meva"))
        return members
    }

    private fun refreshAdapterCenter(members: ArrayList<MemberCenter>) {
        val adapter = HomeAdapterCenter(requireContext(), members)
        binding.rvHome2.adapter = adapter
    }

    private fun prepareMemerListCenter(): ArrayList<MemberCenter> {
        val members : java.util.ArrayList<MemberCenter> = java.util.ArrayList()
        members.add(MemberCenter("https://ae04.alicdn.com/kf/Sb561ad725978458b97d7a35c9f7538f1X.jpg_640x640.jpg", "Gullar","",""))
        members.add(MemberCenter("https://ae04.alicdn.com/kf/Sad59ba71023042348948ef4336ae399ee.jpg_640x640.jpg", "To'rt","",""))
        members.add(MemberCenter("https://ae04.alicdn.com/kf/S22fb60e2e67f4cdeacc93103e367894af.jpg_640x640.jpg", "Gullar","",""))
        members.add(MemberCenter("https://ae04.alicdn.com/kf/Heab259f697a34ffcbb9f18ac726cb4a38.jpg_640x640.jpg", "Meva","",""))
        return members
    }

    private fun refreshAdapterBottom(members: ArrayList<MemberCenter>) {
        val adapter = HomeAdapterBottom(requireContext(), members)
        binding.rvHome3.adapter = adapter
    }

    private fun prepareMemerListBottom(): ArrayList<MemberCenter> {
        val members : java.util.ArrayList<MemberCenter> = java.util.ArrayList()
        members.add(MemberCenter("https://ae04.alicdn.com/kf/Sb561ad725978458b97d7a35c9f7538f1X.jpg_640x640.jpg", "Gullar","",""))
        members.add(MemberCenter("https://ae04.alicdn.com/kf/Sad59ba71023042348948ef4336ae399ee.jpg_640x640.jpg", "To'rt","",""))
        members.add(MemberCenter("https://ae04.alicdn.com/kf/S22fb60e2e67f4cdeacc93103e367894af.jpg_640x640.jpg", "Gullar","",""))
        members.add(MemberCenter("https://ae04.alicdn.com/kf/Heab259f697a34ffcbb9f18ac726cb4a38.jpg_640x640.jpg", "Meva","",""))
        return members
    }
}