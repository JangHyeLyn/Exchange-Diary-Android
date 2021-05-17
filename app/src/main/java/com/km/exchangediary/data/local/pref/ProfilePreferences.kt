package com.km.exchangediary.data.local.pref

import android.content.Context
import android.content.SharedPreferences
import com.km.exchangediary.ui.profile.ProfileDataClass

class ProfilePreferences(context: Context) {
    private val sharedPref: SharedPreferences =
        context.getSharedPreferences("profile_pref", Context.MODE_PRIVATE)

    fun getProfileData(): ProfileDataClass =
        ProfileDataClass(
            sharedPref.getString("name", "") ?: "",
            sharedPref.getString("introduction", "") ?: "",
            sharedPref.getString("profileImage", "") ?: ""
        )

    fun setProfileData(profileData: ProfileDataClass) {
        val editor = sharedPref.edit()
        editor.putString("name", profileData.name)
        editor.putString("introduction", profileData.introduction)
        editor.putString("profileImage", profileData.profileImage)

        editor.apply()
    }
}