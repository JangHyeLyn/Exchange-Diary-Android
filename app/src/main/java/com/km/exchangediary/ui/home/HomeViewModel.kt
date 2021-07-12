package com.km.exchangediary.ui.home

import com.km.exchangediary.base.BaseViewModel
import com.km.exchangediary.domain.usecase.GetJoinedDiaryListUseCase
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel(private val getJoinedDiaryListUseCase: GetJoinedDiaryListUseCase): BaseViewModel() {

    fun getJoinedDiaryList() {
        launch {
            for (item in getJoinedDiaryListUseCase.getJoinedDiaryList()) {
                Timber.d("${item.title}: ${item.group}")
            }
        }
    }
}