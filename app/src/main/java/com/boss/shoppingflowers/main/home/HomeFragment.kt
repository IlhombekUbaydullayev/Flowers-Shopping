package com.boss.shoppingflowers.main.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.boss.shoppingflowers.BaseFragment
import com.boss.shoppingflowers.R
import com.boss.shoppingflowers.adapter.HomeAdapterBottom
import com.boss.shoppingflowers.adapter.HomeAdapterCenter
import com.boss.shoppingflowers.databinding.FragmentHomeOrgBinding
import com.boss.shoppingflowers.managers.DatabaseManager
import com.boss.shoppingflowers.managers.PrefsManager
import com.boss.shoppingflowers.managers.handler.DBMarketsHandler
import com.boss.shoppingflowers.managers.handler.DBPostsHandler
import com.boss.shoppingflowers.model.Markets
import com.boss.shoppingflowers.model.Products

class HomeFragment : BaseFragment(R.layout.fragment_home_org) {
    private val binding by viewBinding(FragmentHomeOrgBinding::bind)
    private var homeAdapterCenter : HomeAdapterCenter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        }
        requireActivity().getOnBackPressedDispatcher().addCallback(requireActivity(), onBackPressedCallback)
        loadLocale()
        initViews()
    }


    private fun initViews() {
        PrefsManager(requireContext()).storeLong("1")
        binding.rvHome2.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvHome3.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        loadMyFeeds()
        loadMarkets()
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun refreshAdapterCenter(members: ArrayList<Products>) {
        val counter = members.size-1
        if (counter > 10) {
            homeAdapterCenter = HomeAdapterCenter(requireContext(),members.subList(0,10)) { seletedItem: Products ->
                openDetails(
                    seletedItem
                )
            }
        }else {
            homeAdapterCenter = HomeAdapterCenter(requireContext(),members.subList(0,counter)) { seletedItem: Products ->
                openDetails(
                    seletedItem
                )
            }
        }
        binding.rvHome2.adapter = homeAdapterCenter
    }

    private fun openDetails(seletedItem: Products) {
        Log.d("seletedItem","P -- $seletedItem")
        tansactionToDetailsInCenter(requireContext(),seletedItem)
    }

    private fun loadMarkets() {
        DatabaseManager.loadFeeds(object : DBMarketsHandler {
            override fun onSuccess(post: ArrayList<Markets>) {
                Log.d("posts", post.toString())
                goneProgress()
                refreshAdapterBottom(post)
            }

            override fun onError(e: Exception) {

            }
        })
    }

    private fun loadMyFeeds() {
        DatabaseManager.loadFeeds(object : DBPostsHandler {
            override fun onSuccess(posts: ArrayList<Products>) {
                goneProgress()
                Log.d("posts", posts.toString())
                refreshAdapterCenter(posts)
            }

            override fun onError(e: Exception) {

            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun refreshAdapterBottom(members: ArrayList<Markets>) {
        val adapter = HomeAdapterBottom(requireContext(), members)
        binding.rvHome3.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    fun visibleProgress() {
        binding.flProgress.visibility = View.VISIBLE
    }

    fun goneProgress() {
        binding.flProgress.visibility = View.GONE
    }
}