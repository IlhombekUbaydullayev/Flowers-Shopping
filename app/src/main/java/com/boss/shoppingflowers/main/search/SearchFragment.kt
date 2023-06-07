package com.boss.shoppingflowers.main.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.boss.shoppingflowers.BaseFragment
import com.boss.shoppingflowers.R
import com.boss.shoppingflowers.adapter.SearchAdapter
import com.boss.shoppingflowers.databinding.FragmentSearchBinding
import com.boss.shoppingflowers.managers.PrefsManager
import com.boss.shoppingflowers.model.Products
import com.boss.shoppingflowers.viewmodels.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SearchFragment : BaseFragment(R.layout.fragment_search) {

    lateinit var linearLayoutManage : GridLayoutManager
    private val viewModel by viewModels<ProductsViewModel>()
    private var adapter : SearchAdapter? = null
    private val binding by viewBinding(FragmentSearchBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        PrefsManager(requireContext()).storeLong("2")
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_homeFragment3_to_homeFragment2)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)
        initViews()
        setProductsAdapter()
        getProducts()
        setProgressBarAccordingToLoadState()
    }

    private fun initViews() {
        visibleProgress()
        adapter = SearchAdapter(requireContext()){onItemClick : Products -> selectedItem(onItemClick)}
        linearLayoutManage = GridLayoutManager(requireContext(),2)
        val animation =
            AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down)
        binding.rvSearch.layoutAnimation = animation
        binding.apply {
            rvSearch.layoutManager = linearLayoutManage
//            etSearch.addTextChangedListener(object : TextWatcher {
//
//                override fun afterTextChanged(s: Editable) {}
//
//                override fun beforeTextChanged(s: CharSequence, start: Int,
//                                               count: Int, after: Int) {
//                }
//
//                override fun onTextChanged(s: CharSequence, start: Int,
//                                           before: Int, count: Int) {
//                    if (s.toString().isNotEmpty()) {
////                        loadMyFeeds(s.toString())
//                    }else {
//                    }
//                }
//            })
        }
    }

    private fun selectedItem(onItemClick: Products) {
        tansactionToDetailsInCenter(requireContext(),onItemClick)
    }

    fun visibleProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    fun goneProgress() {
        binding.progressBar.visibility = View.GONE
    }

    private fun setProductsAdapter() {
        binding.rvSearch.adapter = adapter
    }

    private fun getProducts() {
        lifecycleScope.launch {
            viewModel.flow.collectLatest {
                adapter?.submitData(it)
                Log.d("ItemName",adapter?.itemCount.toString())
            }
        }
    }

    private fun setProgressBarAccordingToLoadState() {
        lifecycleScope.launch {
            adapter?.loadStateFlow?.collectLatest {
                binding.progressBar.isVisible = it.append is LoadState.Loading
                Log.d("ItemName",adapter?.itemCount.toString())
            }
        }
    }
}