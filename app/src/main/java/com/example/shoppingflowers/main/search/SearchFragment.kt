package com.example.shoppingflowers.main.search

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.shoppingflowers.R
import com.example.shoppingflowers.adapter.HomeAdapterCenter
import com.example.shoppingflowers.adapter.SearchAdapter
import com.example.shoppingflowers.databinding.FragmentHomeBinding
import com.example.shoppingflowers.databinding.FragmentSearchBinding
import com.example.shoppingflowers.modeltest.MemberCenter

class SearchFragment : Fragment(R.layout.fragment_search) {
    private val binding by viewBinding(FragmentSearchBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        val members2 = prepareMemerList()
        refreshAdapter(members2)
    }

    private fun initViews() {
        // This callback will only be called when MyFragment is at least Started.
        // This callback will only be called when MyFragment is at least Started.
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {

                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(), callback)
        binding.rvSearch.layoutManager = GridLayoutManager(requireContext(),2)
    }


    private fun refreshAdapter(members: ArrayList<MemberCenter>) {
        val adapter = SearchAdapter(requireContext(), members)
        binding.rvSearch.adapter = adapter
    }

    private fun prepareMemerList(): ArrayList<MemberCenter> {
        val members : java.util.ArrayList<MemberCenter> = java.util.ArrayList()
        members.add(MemberCenter("https://ae04.alicdn.com/kf/Sb561ad725978458b97d7a35c9f7538f1X.jpg_640x640.jpg", "Gullar","",""))
        members.add(MemberCenter("https://ae04.alicdn.com/kf/Sad59ba71023042348948ef4336ae399ee.jpg_640x640.jpg", "To'rt","",""))
        members.add(MemberCenter("https://ae04.alicdn.com/kf/S22fb60e2e67f4cdeacc93103e367894af.jpg_640x640.jpg", "Gullar","",""))
        members.add(MemberCenter("https://ae04.alicdn.com/kf/Heab259f697a34ffcbb9f18ac726cb4a38.jpg_640x640.jpg", "Meva","",""))
        members.add(MemberCenter("https://ae04.alicdn.com/kf/Heab259f697a34ffcbb9f18ac726cb4a38.jpg_640x640.jpg", "Meva","",""))
        members.add(MemberCenter("https://ae04.alicdn.com/kf/Sb561ad725978458b97d7a35c9f7538f1X.jpg_640x640.jpg", "Gullar","",""))
        members.add(MemberCenter("https://ae04.alicdn.com/kf/Sb561ad725978458b97d7a35c9f7538f1X.jpg_640x640.jpg", "Gullar","",""))
        members.add(MemberCenter("https://ae04.alicdn.com/kf/Sb561ad725978458b97d7a35c9f7538f1X.jpg_640x640.jpg", "Gullar","",""))
        return members
    }

}