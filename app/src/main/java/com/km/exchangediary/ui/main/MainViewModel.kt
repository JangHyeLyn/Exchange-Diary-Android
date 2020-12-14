package com.km.exchangediary.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.km.exchangediary.base.BaseViewModel
import com.km.exchangediary.domain.usecase.TestUseCase
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(private val testUseCase: TestUseCase) : BaseViewModel() {
    private val _gitRepoString: MutableLiveData<String> = MutableLiveData<String>("헬로 월드")
    val gitRepoString: LiveData<String> get() = _gitRepoString

    fun getRepoName(flag: Int) {
        launch {
            _gitRepoString.value = testUseCase.get(flag)
            Timber.d(_gitRepoString.value)
        }
    }
}