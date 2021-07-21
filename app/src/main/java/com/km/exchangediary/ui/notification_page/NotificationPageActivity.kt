package com.km.exchangediary.ui.notification_page

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseActivity
import com.km.exchangediary.databinding.ActivityNotificationPageBinding
import com.km.exchangediary.ui.notification_page.model.NotificationList
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotificationPageActivity : BaseActivity<ActivityNotificationPageBinding>() {
    override fun layoutRes(): Int = R.layout.activity_notification_page
    private val viewModel: NotificationViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnNotificationBack.setOnClickListener { finish() }

        viewModel.notificationList.observe(this, Observer { response ->
            onBindView(response)
        })
    }

    private fun onBindView(notificationList: List<NotificationList>) {
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