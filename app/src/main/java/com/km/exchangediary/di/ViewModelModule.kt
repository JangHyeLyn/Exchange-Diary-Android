package com.km.exchangediary.di

import com.km.exchangediary.ui.diary.CoverViewModel
import com.km.exchangediary.ui.diary.DiaryViewModel
import com.km.exchangediary.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get())}
    viewModel { DiaryViewModel()}
    viewModel { CoverViewModel()}
}