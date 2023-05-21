package com.boss.shoppingflowers.main.intro

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.boss.shoppingflowers.R
import com.boss.shoppingflowers.databinding.FragmentIntroSecondaryBinding

class IntroFragmentXL : Fragment(R.layout.fragment_intro_secondary) {
    private val binding by viewBinding(FragmentIntroSecondaryBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
    }

}