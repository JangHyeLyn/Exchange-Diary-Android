package com.km.exchangediary.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.km.exchangediary.base.BaseViewModel

class MainViewModel : BaseViewModel() {
    private val _hello: MutableLiveData<String> = MutableLiveData<String>("헬로 월드")
    val hello: LiveData<String> get() = _hello
}