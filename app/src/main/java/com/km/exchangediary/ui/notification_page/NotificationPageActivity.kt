package com.km.exchangediary.ui.notification_page

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseActivity
import com.km.exchangediary.data.remote.response.NotificationResponse
import com.km.exchangediary.databinding.ActivityNotificationPageBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotificationPageActivity : BaseActivity<ActivityNotificationPageBinding>() {
    override fun layoutRes(): Int = R.layout.activity_notification_page
    private val viewModel: NotificationViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.ivProfileBack.setOnClickListener { finish() }

        //var notificationList = viewModel.getNotificationList(onBindView(it))
    }

    private fun onBindView(notificationList: NotificationResponse) {
        binding.rvNotificationList.apply {
            adapter = NotificationListAdapter(notificationList)
            layoutManager = LinearLayoutManager(
                this@NotificationPageActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }
}