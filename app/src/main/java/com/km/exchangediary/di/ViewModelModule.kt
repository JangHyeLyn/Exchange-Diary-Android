package com.km.exchangediary.di

import com.km.exchangediary.ui.diary.CoverViewModel
import com.km.exchangediary.ui.diary.DiaryViewModel
import com.km.exchangediary.ui.group_management.GroupManagementViewModel
import com.km.exchangediary.ui.login.LoginViewModel
import com.km.exchangediary.ui.notification_page.NotificationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { DiaryViewModel() }
    viewModel { CoverViewModel() }
    viewModel { LoginViewModel(get()) }
    viewModel { GroupManagementViewModel(get(), get(), get(), get(), get()) }
    viewModel { NotificationViewModel( get()) }
}