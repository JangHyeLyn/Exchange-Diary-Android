package com.km.exchangediary.ui.notification_page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.km.exchangediary.base.BaseViewModel
import com.km.exchangediary.domain.usecase.NotificationPageUseCase
import com.km.exchangediary.ui.notification_page.model.NotificationList
import kotlinx.coroutines.launch

class NotificationViewModel(
    private val notificationPageUseCase: NotificationPageUseCase
) : BaseViewModel() {
    private val _notificationList = MutableLiveData<List<NotificationList>>()
    val notificationList: LiveData<List<NotificationList>> = _notificationList

    init {
        getNotificationList()
    }

    private fun getNotificationList() {
        launch {
            _notificationList.value = notificationPageUseCase.getNotificationList()
        }
    }
}
