package com.example.shoppingflowers.main.intro

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.shoppingflowers.R
import com.example.shoppingflowers.databinding.FragmentIntroSecondaryBinding
import com.example.shoppingflowers.manager.AuthManager

class IntroFragmentXL : Fragment(R.layout.fragment_intro_secondary) {
    private val binding by viewBinding(FragmentIntroSecondaryBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {

    }
}