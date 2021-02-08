package com.km.exchangediary.ui.home

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseActivity
import com.km.exchangediary.databinding.ActivityHomeBinding
import com.km.exchangediary.ui.home.adapter.HomeDiaryListAdapter
import com.km.exchangediary.ui.home.adapter.HomeWritingDiaryAdapter
import com.km.exchangediary.ui.home.itemdecoration.HomeWritingDiaryDecoration

class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    override fun layoutRes(): Int = R.layout.activity_home

    /* TODO: 그룹 관리가 만들어지면 그룹 관리와 엮자 */
    private val groupNameList = arrayListOf("전체", "그룹 미지정", "평균 28세들", "그룹명최대열..", "돼지파티")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBindView()
    }

    private fun onBindView() {
        binding.rvWritingDiary.apply {
            adapter = HomeWritingDiaryAdapter()
            layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(HomeWritingDiaryDecoration())
        }

        binding.vpDiaryList.apply {
            adapter = HomeDiaryListAdapter(supportFragmentManager, this@HomeActivity.lifecycle)
            isUserInputEnabled = false
        }

        TabLayoutMediator(binding.tabDiaryList, binding.vpDiaryList) { tab, position ->
            tab.text = groupNameList[position]
        }.attach()
    }
}