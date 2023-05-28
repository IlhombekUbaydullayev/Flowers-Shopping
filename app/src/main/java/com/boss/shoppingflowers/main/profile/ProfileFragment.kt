package com.boss.shoppingflowers.main.profile

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.boss.shoppingflowers.R
import com.boss.shoppingflowers.databinding.FragmentHomeBinding
import com.boss.shoppingflowers.managers.PrefsManager

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val binding by viewBinding(FragmentHomeBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val cancel = PrefsManager(requireContext()).loadLong()
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (cancel == "1") {
                    findNavController().navigate(R.id.action_profile_to_homeFragment)
                }else {
                    findNavController().navigate(R.id.action_homeFragment3_to_homeFragment1)
                }
                PrefsManager(requireContext()).storeLong("3")
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)
    }

}