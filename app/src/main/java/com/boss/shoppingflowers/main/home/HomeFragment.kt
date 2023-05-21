package com.boss.shoppingflowers.main.home

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
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

class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private var context: Context? = null
    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Toast.makeText(requireContext(),PrefsManager(requireContext()).loadLong(),Toast.LENGTH_SHORT).show()
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        }
        requireActivity().getOnBackPressedDispatcher().addCallback(this, onBackPressedCallback)
        loadLocale()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        val members = prepareMemerList()
        refreshAdapter(members)
    }


    private fun initViews() {
        PrefsManager(requireContext()).storeLong("1")
        binding.apply {
            llV1.setOnClickListener {
                visibleProgress()
                tvFlower.setTextColor(Color.RED)
                tvFlower2.setTextColor(Color.DKGRAY)
                tvFlower3.setTextColor(Color.DKGRAY)
                loadMyFeeds(Type.FLOWERS.toString())
            }
            llV2.setOnClickListener {
                visibleProgress()
                tvFlower.setTextColor(Color.DKGRAY)
                tvFlower2.setTextColor(Color.RED)
                tvFlower3.setTextColor(Color.DKGRAY)
                loadMyFeeds(Type.CAKES.toString())
            }
            llV3.setOnClickListener {
                visibleProgress()
                tvFlower.setTextColor(Color.DKGRAY)
                tvFlower2.setTextColor(Color.DKGRAY)
                tvFlower3.setTextColor(Color.RED)
                loadMyFeeds(Type.FRUITS.toString())
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
        val adapter = HomeAdapter(requireContext(), this, members)
        binding.rvHome.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun prepareMemerList(): ArrayList<Member> {
        val members: java.util.ArrayList<Member> = java.util.ArrayList()
        members.add(
            Member(
                "https://ae04.alicdn.com/kf/Sb561ad725978458b97d7a35c9f7538f1X.jpg_640x640.jpg",
                resources.getString(R.string.str_flowers)
            )
        )
        members.add(
            Member(
                "https://ae04.alicdn.com/kf/Sef4c51849fef4c4fb79976ba891713b0K.jpg_640x640.jpg",
                resources.getString(R.string.cakes)
            )
        )
        members.add(
            Member(
                "https://media.istockphoto.com/id/173255460/photo/assortment-of-fruits.jpg?b=1&s=170667a&w=0&k=20&c=DTUxwA3VoqtIwRHy9mwFi8vFeMlPtrwULj8KJkeiwlE=",
                resources.getString(R.string.fruits)
            )
        )
        return members
    }

    private fun refreshAdapterCenter(members: ArrayList<Products>) {
        val adapter = HomeAdapterCenter(requireContext(), members)
        binding.rvHome2.adapter = adapter
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

    private fun refreshAdapterBottom(members: ArrayList<Markets>) {
        val adapter = HomeAdapterBottom(requireContext(), members)
        binding.rvHome3.adapter = adapter
    }

    public fun onClick(item : Int) {
        when(item) {
            0 -> {
                visibleProgress()
                loadMyFeeds(Type.FLOWERS.toString())
            }
            1 -> {
                visibleProgress()
                loadMyFeeds(Type.CAKES.toString())
            }
            2 -> {
                visibleProgress()
                loadMyFeeds(Type.FRUITS.toString())
            }
        }
    }

    fun visibleProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    fun goneProgress() {
        binding.progressBar.visibility = View.GONE
    }
}