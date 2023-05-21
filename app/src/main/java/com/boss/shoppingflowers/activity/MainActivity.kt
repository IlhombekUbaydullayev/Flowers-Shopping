package com.boss.shoppingflowers.activity

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.boss.shoppingflowers.R
import com.boss.shoppingflowers.databinding.ActivityMainBinding
import com.boss.shoppingflowers.managers.AuthManager

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadLocale()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val navGraph = navController.navInflater.inflate(R.navigation.naw_graph)
        navGraph.setStartDestination(R.id.splashFlowFragment)
        when {
            AuthManager.isAuthorized == 0 -> {
                navGraph.setStartDestination(R.id.splashFlowFragment)
            }

            AuthManager.isAuthorized == 1 -> {
                navGraph.setStartDestination(R.id.introFlowFragment)
            }
//            AuthManager.isAuthorized == 2 -> {
//                navGraph.setStartDestination(R.id.introFlowFragmentXL)
//            }
            AuthManager.isAuthorized == 3 -> {
                navGraph.setStartDestination(R.id.mainFlowFragment)
            }
        }
        navController.graph = navGraph
    }
}