package com.boss.shoppingflowers

import android.app.ActivityOptions
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.boss.shoppingflowers.activity.DetailsActivity
import com.boss.shoppingflowers.model.Products
import java.util.Locale

open class BaseFragment(fragmentHome: Int) : Fragment(fragmentHome) {
    open fun setLocale(language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = Configuration()
        requireActivity().baseContext.resources.updateConfiguration(config,
            requireActivity().baseContext.resources.displayMetrics)
        val editor : SharedPreferences.Editor = requireActivity().getSharedPreferences("Setting",
            Context.MODE_PRIVATE
        ).edit()
        editor.putString("My_Lang",language)
        editor.apply()
    }

    open fun loadLocale() {
        val prefs = requireActivity().getSharedPreferences("Setting", Context.MODE_PRIVATE)
        val language = prefs.getString("My_Lang","")
        setLocale(language!!)
    }

    open fun showChangeDialogLanguage() {
        val list = arrayOf("English","Russian","Uzbek")
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Choose Language...")
        alertDialog.setSingleChoiceItems(list,-1, DialogInterface.OnClickListener { dialog, which ->
            if (which == 0) {
                setLocale("en")
                requireActivity().recreate()
            }else if (which == 1) {
                setLocale("ru")
                requireActivity().recreate()
            }else if (which == 2) {
                setLocale("uz")
                requireActivity().recreate()
            }

            dialog.dismiss()
        })
        val mDialog = alertDialog.create()
        mDialog.show()
    }

    fun tansactionToDetailsInCenter(context : Context,product : Products) {
        val intent = Intent(context, DetailsActivity::class.java)
        intent.putExtra("item_center", product)
        val options = ActivityOptions.makeSceneTransitionAnimation(
            activity
        )
        startActivity(intent,options.toBundle())
    }
}