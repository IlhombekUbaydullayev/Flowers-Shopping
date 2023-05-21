package com.boss.shoppingflowers.managers

import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings
class PrefsManager(context: Context) {
    val sharedPreferences: SharedPreferences?

    init {
        sharedPreferences = context.getSharedPreferences("insta_db", Context.MODE_PRIVATE)
    }

    fun storeDeviceToken(token: String?) {
        val prefsEditor = sharedPreferences!!.edit()
        prefsEditor.putString("device_token", token)
        prefsEditor.apply()
    }

    fun storeLong(token: String?) {
        val prefsEditor = sharedPreferences!!.edit()
        prefsEditor.putString("navigate", token)
        prefsEditor.apply()
    }

    fun loadLong(): String? {
        return sharedPreferences!!.getString("navigate", "")
    }

    fun loadDeviceToken(): String? {
        return sharedPreferences!!.getString("device_token", "")
    }
}