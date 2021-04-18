package com.km.exchangediary.ui.login

import android.os.Bundle
import android.view.View
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseFragment
import com.km.exchangediary.databinding.FragmentOnBoardingBinding

class OnBoardingFragment(private val currentPageIndex: Int): BaseFragment<FragmentOnBoardingBinding>() {
    override fun layoutRes(): Int = R.layout.fragment_on_boarding

    private val onBoardingImageList = arrayListOf(
        R.drawable.illust_onboarding_01,
        R.drawable.illust_onboarding_02,
        R.drawable.illust_onboarding_03
    )
    private val titleList = arrayListOf(
        "친구와 함께하는\n교환일기",
        "아날로그 감성\n그대로",
        "다양한 일기장에\n가볍게"
    )
    private val contentsList = arrayListOf(
        "같이 이야기 나누고 싶은 친구를 초대해보세요!",
        "내 차례를 기다리고, 친구를 재촉할 수도 있어요!",
        "무겁지 않아도 괜찮아요! 부담없이 작성해보세요 :)"
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivOnBoarding.setImageResource(onBoardingImageList[currentPageIndex])
        binding.tvTitle.text = titleList[currentPageIndex]
        binding.tvContents.text = contentsList[currentPageIndex]
    }
}