package com.boss.shoppingflowers

import android.app.Application
import android.content.Context
import com.boss.shoppingflowers.utils.SampleClass
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class ProductApplication : Application(){

    @Inject
    lateinit var sampleClass: SampleClass

    companion object {
        var context: Context? = null
    }
    override fun onCreate() {
        super.onCreate()
        sampleClass.doSomething()
        context = this
    }
}