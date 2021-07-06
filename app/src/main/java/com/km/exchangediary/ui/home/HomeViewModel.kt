package com.km.exchangediary.ui.home

import com.km.exchangediary.base.BaseViewModel
import com.km.exchangediary.domain.usecase.GetJoinedDiaryListUseCase
import kotlinx.coroutines.launch

class HomeViewModel(private val getJoinedDiaryListUseCase: GetJoinedDiaryListUseCase): BaseViewModel() {

    fun getJoinedDiaryList() {
        launch {
            getJoinedDiaryListUseCase.getJoinedDiaryList()
        }
    }
}