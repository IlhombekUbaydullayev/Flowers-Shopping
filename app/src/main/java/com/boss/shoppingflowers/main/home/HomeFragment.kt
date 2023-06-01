package com.boss.shoppingflowers.main.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.boss.shoppingflowers.BaseFragment
import com.boss.shoppingflowers.R
import com.boss.shoppingflowers.adapter.HomeAdapter
import com.boss.shoppingflowers.adapter.HomeAdapterBottom
import com.boss.shoppingflowers.adapter.HomeAdapterCenter
import com.boss.shoppingflowers.databinding.FragmentHomeBinding
import com.boss.shoppingflowers.enums.Type
import com.boss.shoppingflowers.managers.DatabaseManager
import com.boss.shoppingflowers.managers.PrefsManager
import com.boss.shoppingflowers.managers.handler.DBMarketsHandler
import com.boss.shoppingflowers.managers.handler.DBPostsHandler
import com.boss.shoppingflowers.model.Markets
import com.boss.shoppingflowers.model.Products
import com.boss.shoppingflowers.modeltest.Member
import com.boss.shoppingflowers.utils.DataEntry
import java.util.Random

class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private var homeAdapterCenter : HomeAdapterCenter? = null
    var adapter : HomeAdapter? = null
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
        val members = DataEntry.prepareMemerList()
        refreshAdapter(members)
    }


    private fun initViews() {
        PrefsManager(requireContext()).storeLong("1")
        binding.apply {
            llV1.setOnClickListener {
                setColor(0)
            }
            llV2.setOnClickListener {
                setColor(1)
            }
            llV3.setOnClickListener {
                setColor(2)
            }
            ivLanguages.setOnClickListener {
                showChangeDialogLanguage()
            }
        }

        binding.rvHome.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvHome2.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvHome3.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val manager = PrefsManager(requireContext()).loadDeviceToken()
        loadMyFeeds(manager)
        loadMarkets()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun refreshAdapter(members: ArrayList<Member>) {
        adapter = HomeAdapter(this,
            members)
        binding.rvHome.adapter = adapter
        adapter?.notifyDataSetChanged()
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
        adapter?.notifyDataSetChanged()
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

    private fun loadMyFeeds(manager: String?) {
        DatabaseManager.loadFeeds(manager!!,object : DBPostsHandler {
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

    public fun onClick(item: Int) {
        Log.d("ItemCounts",item.toString())
        when(item) {
            0 -> {
                visibleProgress()
                loadMyFeeds(Type.FLOWERS.toString())
                setColor(item)
            }
            1 -> {
                visibleProgress()
                loadMyFeeds(Type.CAKES.toString())
                setColor(item)
            }
            2 -> {
                visibleProgress()
                loadMyFeeds(Type.FRUITS.toString())
                setColor(item)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setColor(i: Int) {
        when(i) {
            0 -> {
                binding.apply {
                    visibleProgress()
                    tvFlower.setTextColor(Color.RED)
                    tvFlower2.setTextColor(Color.DKGRAY)
                    tvFlower3.setTextColor(Color.DKGRAY)
                    loadMyFeeds(Type.FLOWERS.toString())
                }
            }

            1 -> {
                binding.apply {
                    visibleProgress()
                    tvFlower.setTextColor(Color.DKGRAY)
                    tvFlower2.setTextColor(Color.RED)
                    tvFlower3.setTextColor(Color.DKGRAY)
                    loadMyFeeds(Type.CAKES.toString())
                }
            }

            2 -> {
                binding.apply {
                    visibleProgress()
                    tvFlower.setTextColor(Color.DKGRAY)
                    tvFlower2.setTextColor(Color.DKGRAY)
                    tvFlower3.setTextColor(Color.RED)
                    loadMyFeeds(Type.FRUITS.toString())
                }
            }
        }
        adapter!!.itemCounts = i
        adapter!!.notifyDataSetChanged()
        if (i == adapter!!.itemCounts) {
            adapter!!.tv_center!!.setTextColor(Color.RED)
        }else{
            adapter!!.tv_center!!.setTextColor(Color.BLACK)
        }
    }

    fun visibleProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    fun goneProgress() {
        binding.progressBar.visibility = View.GONE
    }
}