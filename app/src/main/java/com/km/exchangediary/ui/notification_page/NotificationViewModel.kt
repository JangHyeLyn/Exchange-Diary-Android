package com.km.exchangediary.ui.notification_page

import android.util.Log
import androidx.lifecycle.ViewModel
import com.km.exchangediary.data.local.pref.LoginPreferences
import com.km.exchangediary.data.remote.datasource.ExchangeDiaryDataSource
import com.km.exchangediary.data.remote.response.NotificationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationViewModel(
    private val dataSource: ExchangeDiaryDataSource,
    private val loginPreferences: LoginPreferences
) : ViewModel() {
    fun getNotificationList(success: (response: NotificationResponse) -> Unit) {
        val service = dataSource.getExchangeDiaryService()
        val token = "jwt ${loginPreferences.getUserToken()}"

        service.getNotifications(token).enqueue(object : Callback<NotificationResponse> {
            override fun onResponse(
                call: Call<NotificationResponse>,
                response: Response<NotificationResponse>
            ) {
                Log.d("responsebody", response.body().toString())
                val notificationResponse = response.body()!!
                success(notificationResponse)
            }

            override fun onFailure(call: Call<NotificationResponse>, t: Throwable) {
                Log.d("onfailure", "실패 : $t")
            }
        })
    }
}
