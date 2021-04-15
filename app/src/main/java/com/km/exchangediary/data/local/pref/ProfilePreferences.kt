package com.km.exchangediary.data.local.pref

import android.content.Context
import android.content.SharedPreferences

class ProfilePreferences(private val context: Context) {
    private val sharedPref: SharedPreferences =
        context.getSharedPreferences("profile_pref", Context.MODE_PRIVATE)

    fun getProfile(key: String): String = sharedPref.getString(key, "").toString()

    fun setProfile(key: String, str: String) {
        sharedPref.edit().putString(key, str).apply()
    }
}