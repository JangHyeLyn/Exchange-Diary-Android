package com.km.exchangediary.data.local.pref

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class LoginPreferences(private val context: Context) {
    private val sharedPref: SharedPreferences = context.getSharedPreferences("login_pref", MODE_PRIVATE)

    fun getUserToken() = sharedPref.getString("jwt", "")

    fun setUserToken(userToken: String) {
        sharedPref.edit().putString("jwt", userToken).apply()
    }
}