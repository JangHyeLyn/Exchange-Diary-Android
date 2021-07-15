package com.km.exchangediary.data.repository

import com.km.exchangediary.data.local.pref.LoginPreferences
import com.km.exchangediary.data.local.pref.ProfilePreferences
import com.km.exchangediary.ui.profile.ProfileDataClass

class ProfileRepository(
    private val profilePreferences: ProfilePreferences,
    private val loginPreferences: LoginPreferences
) {
    fun setProfileData(profileData: ProfileDataClass) {
        profilePreferences.setProfileData(profileData)
    }

    fun getProfileData(): ProfileDataClass = profilePreferences.getProfileData()

    fun getJWT(): String = loginPreferences.getUserToken() ?: "INVALID"
}