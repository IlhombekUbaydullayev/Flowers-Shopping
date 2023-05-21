package com.boss.shoppingflowers.main.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.boss.shoppingflowers.R
import com.boss.shoppingflowers.adapter.SearchAdapter
import com.boss.shoppingflowers.databinding.FragmentSearchBinding
import com.boss.shoppingflowers.managers.DatabaseManager
import com.boss.shoppingflowers.managers.PrefsManager
import com.boss.shoppingflowers.managers.handler.DBPostsHandler
import com.boss.shoppingflowers.model.Products
import com.boss.shoppingflowers.modeltest.MemberCenter

class SearchFragment : Fragment(R.layout.fragment_search) {
    private val binding by viewBinding(FragmentSearchBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PrefsManager(requireContext()).storeLong("2")
//        Toast.makeText(requireContext(),PrefsManager(requireContext()).loadLong(), Toast.LENGTH_SHORT).show()
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_homeFragment3_to_homeFragment2)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        getAllProduct()
    }

    private fun initViews() {
        binding.apply {
            rvSearch.layoutManager = GridLayoutManager(requireContext(),2)
            etSearch.addTextChangedListener(object : TextWatcher {

                override fun afterTextChanged(s: Editable) {}

                override fun beforeTextChanged(s: CharSequence, start: Int,
                                               count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence, start: Int,
                                           before: Int, count: Int) {
                    if (s.toString().isNotEmpty()) {
                        loadMyFeeds(s.toString())
                    }else {
                        getAllProduct()
                    }
                }
            })
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun refreshAdapter(members: ArrayList<Products>) {
        val adapter = SearchAdapter(requireContext(), members)
        binding.rvSearch.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun loadMyFeeds(manager: String?) {
        DatabaseManager.searchFeeds(manager!!,object : DBPostsHandler {
            override fun onSuccess(posts: ArrayList<Products>) {
                Log.d("posts", posts.toString())
                goneProgress()
                refreshAdapter(posts)
            }

            override fun onError(e: Exception) {

            }
        })
    }

    private fun getAllProduct() {
        DatabaseManager.getAllProduct(object : DBPostsHandler {
            override fun onSuccess(posts: ArrayList<Products>) {
                Log.d("posts", posts.toString())
                refreshAdapter(posts)
                goneProgress()
            }

            override fun onError(e: Exception) {

            }
        })
    }

    fun visibleProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    fun goneProgress() {
        binding.progressBar.visibility = View.GONE
    }
}