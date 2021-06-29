package com.km.exchangediary.di

import com.km.exchangediary.ui.diary.CoverViewModel
import com.km.exchangediary.ui.diary.DiaryViewModel
import com.km.exchangediary.ui.group_management.GroupManagementViewModel
import com.km.exchangediary.ui.login.LoginViewModel
import com.km.exchangediary.ui.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { DiaryViewModel() }
    viewModel { CoverViewModel() }
    viewModel { LoginViewModel(get()) }
    viewModel { GroupManagementViewModel(get(), get(), get(), get(), get()) }
    viewModel { ProfileViewModel(get(), get()) }
}