package com.km.exchangediary.ui.home

import android.os.Bundle
import android.widget.Toast
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseActivity
import com.km.exchangediary.databinding.ActivityHomeBinding
import com.km.exchangediary.ui.CommonDialog
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
        CommonDialog(
            titleText = "그룹명 수정",
            contentText = "모든 멤버를 통틀어서 하루에 3번 재촉 가능합니다.",
            highlightText = listOf(Pair(0, 6)),
            onSuccess = { Toast.makeText(baseContext, "확인", Toast.LENGTH_SHORT).show() }
        ).show(supportFragmentManager, "good")
    }

    private fun onBindView() {
        binding.rvWritingDiary.apply {
            adapter = HomeWritingDiaryAdapter()
            layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(HomeWritingDiaryDecoration())
        }

        checkWritingDiaryExist(binding.rvWritingDiary.adapter?.itemCount != 0)

        binding.vpDiaryList.apply {
            adapter = HomeDiaryListAdapter(supportFragmentManager, this@HomeActivity.lifecycle)
            isUserInputEnabled = false
        }

        TabLayoutMediator(binding.tabDiaryList, binding.vpDiaryList) { tab, position ->
            tab.text = groupNameList[position]
        }.attach()

        /* TODO: 전체 탭의 소속되어있는 일기장의 개수가 0개일 때로 조건을 변경 */
        checkDiaryListExist(binding.vpDiaryList.adapter?.itemCount != 0)
    }

    private fun checkWritingDiaryExist(isExist: Boolean) {
        if (isExist) {
            binding.layoutEmptyWritingDiary.visibility = View.GONE
            binding.rvWritingDiary.visibility = View.VISIBLE
        } else {
            binding.layoutEmptyWritingDiary.visibility = View.VISIBLE
            binding.rvWritingDiary.visibility = View.GONE
        }
    }

    private fun checkDiaryListExist(isExist: Boolean) {
        if (isExist) {
            binding.layoutEmptyDiaryList.visibility = View.GONE
            binding.vpDiaryList.visibility = View.VISIBLE
        } else {
            binding.layoutEmptyDiaryList.visibility = View.VISIBLE
            binding.vpDiaryList.visibility = View.GONE
        }
    }
}
