package com.km.exchangediary.di

import com.km.exchangediary.ui.diary.CoverViewModel
import com.km.exchangediary.ui.diary.DiaryViewModel
import com.km.exchangediary.ui.main.MainViewModel
import com.km.exchangediary.ui.profile.ProfileEditViewModel
import com.km.exchangediary.ui.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get())}
    viewModel { DiaryViewModel()}
    viewModel { CoverViewModel()}
    viewModel { ProfileViewModel() }
    viewModel { ProfileEditViewModel() }
}