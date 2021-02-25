package com.km.exchangediary.ui.notification_page

import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseActivity
import com.km.exchangediary.databinding.ActivityNotificationPageBinding

class NotificationPageActivity : BaseActivity<ActivityNotificationPageBinding>() {
    override fun layoutRes(): Int = R.layout.activity_notification_page

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.ivProfileBack.setOnClickListener { finish() }

        onBindView()
    }

    private fun onBindView() {
        binding.rvNotificationList.apply {
            adapter = NotificationListAdapter()
            layoutManager = LinearLayoutManager(
                this@NotificationPageActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }
}