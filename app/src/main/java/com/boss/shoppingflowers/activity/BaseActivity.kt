package com.boss.shoppingflowers.activity

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

open class BaseActivity : AppCompatActivity() {
    open fun setLocale(language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = Configuration()
        baseContext.resources.updateConfiguration(
            config,
            baseContext.resources.displayMetrics
        )
        val editor: SharedPreferences.Editor = getSharedPreferences(
            "Setting",
            Context.MODE_PRIVATE
        ).edit()
        editor.putString("My_Lang", language)
        editor.apply()
    }

    open fun loadLocale() {
        val prefs = getSharedPreferences("Setting", Context.MODE_PRIVATE)
        val language = prefs.getString("My_Lang", "")
        setLocale(language!!)
    }
}