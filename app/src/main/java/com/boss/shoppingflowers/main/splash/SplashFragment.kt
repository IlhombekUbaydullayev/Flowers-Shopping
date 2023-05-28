package com.boss.shoppingflowers.main.splash

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.boss.shoppingflowers.R
import com.boss.shoppingflowers.databinding.FragmentSplashBinding
import com.boss.shoppingflowers.extencion.activityNavController
import com.boss.shoppingflowers.extencion.navigateSafely
import com.boss.shoppingflowers.managers.AuthManager
import com.boss.shoppingflowers.managers.PrefsManager

class SplashFragment : Fragment(R.layout.fragment_splash) {
    private val binding by viewBinding(FragmentSplashBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//            activity?.window?.setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN
//        )
        initViews()
    }

    private fun initViews() {
        AuthManager.isAuthorized = 0
        countDownTimer()
    }

    private fun countDownTimer() {
        PrefsManager(requireContext()).storeDeviceToken(com.boss.shoppingflowers.enums.Type.FLOWERS.toString())
        object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                AuthManager.isAuthorized = 1
                activityNavController().navigateSafely(R.id.action_global_introFlow)
            }
        }.start()
    }
}