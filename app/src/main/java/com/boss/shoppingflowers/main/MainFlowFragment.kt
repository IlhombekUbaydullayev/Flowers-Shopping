package com.boss.shoppingflowers.main

import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding

import com.boss.shoppingflowers.BaseFlowFragment
import com.boss.shoppingflowers.R
import com.boss.shoppingflowers.databinding.FlowFragmentMainBinding

class MainFlowFragment : com.boss.shoppingflowers.BaseFlowFragment(
    R.layout.flow_fragment_main, R.id.nav_host_fragment_main
) {

    private val binding by viewBinding(FlowFragmentMainBinding::bind)

    override fun setupNavigation(navController: NavController) {
        binding.bottomNavigation.setupWithNavController(navController)
    }
}