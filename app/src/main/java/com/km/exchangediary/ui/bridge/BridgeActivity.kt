package com.km.exchangediary.ui.bridge

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseActivity
import com.km.exchangediary.databinding.ActivityBridgeBinding
import com.km.exchangediary.ui.notification_page.NotificationPageActivity
import com.km.exchangediary.ui.home.HomeActivity
import com.km.exchangediary.ui.diary.DiaryActivity
import com.km.exchangediary.ui.login.LoginActivity
import com.km.exchangediary.ui.main.MainActivity
import com.km.exchangediary.ui.profile.ProfileActivity

class BridgeActivity : BaseActivity<ActivityBridgeBinding>() {
    override fun layoutRes(): Int = R.layout.activity_bridge

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.rvBridge.apply {
            val bridgeAdapter = BridgeAdapter(this@BridgeActivity)
            bridgeAdapter.activityList = arrayListOf(
                    MainActivity::class.java,
                    HomeActivity::class.java,
                    DiaryActivity::class.java,
                    NotificationPageActivity::class.java,
                    ProfileActivity::class.java,
                    NotificationPageActivity::class.java,
                    LoginActivity::class.java
            )
            adapter = bridgeAdapter
            layoutManager = LinearLayoutManager(this@BridgeActivity)
            setHasFixedSize(true)
        }
    }
}