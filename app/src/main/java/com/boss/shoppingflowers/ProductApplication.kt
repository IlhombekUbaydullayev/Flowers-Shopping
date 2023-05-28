package com.boss.shoppingflowers

import android.app.Application
import com.boss.shoppingflowers.utils.SampleClass
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class ProductApplication : Application(){

    @Inject
    lateinit var sampleClass: SampleClass

    override fun onCreate() {
        super.onCreate()
        sampleClass.doSomething()
    }
}