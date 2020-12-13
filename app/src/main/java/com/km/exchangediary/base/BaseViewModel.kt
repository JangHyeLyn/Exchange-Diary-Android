package com.km.exchangediary.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), CoroutineScope {
    protected val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.Main.immediate + job

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}