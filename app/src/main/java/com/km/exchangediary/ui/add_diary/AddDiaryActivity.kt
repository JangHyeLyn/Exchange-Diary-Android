package com.km.exchangediary.ui.add_diary

import android.os.Bundle
import android.view.KeyEvent
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseActivity
import com.km.exchangediary.databinding.ActivityAddDiaryBinding

/* TODO: sealed class 로 바꿀 수 있을까? */
enum class AddDiaryCurrentFragment {
    CHOOSE_COVER,
    DECORATE_COVER
}

class AddDiaryActivity : BaseActivity<ActivityAddDiaryBinding>() {
    override fun layoutRes(): Int = R.layout.activity_add_diary
    private var currentFragment: AddDiaryCurrentFragment = AddDiaryCurrentFragment.CHOOSE_COVER

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(R.id.layout_container, ChooseCoverFragment.instance).commit()

        binding.btnNext.setOnClickListener {
            when (currentFragment) {
                AddDiaryCurrentFragment.CHOOSE_COVER -> {
                    supportFragmentManager.beginTransaction().replace(R.id.layout_container, DecorateCoverFragment.instance).commit()
                    binding.tvNextButton.text = "완료"
                    currentFragment = AddDiaryCurrentFragment.DECORATE_COVER
                }
                AddDiaryCurrentFragment.DECORATE_COVER -> {
                    // TODO: 다이어리 설정 완료
                    finish()
                }
            }
        }

        binding.btnBack.setOnClickListener {
            pressBackButton()
        }
    }

    private fun pressBackButton() {
        when (currentFragment) {
            AddDiaryCurrentFragment.CHOOSE_COVER -> finish()
            AddDiaryCurrentFragment.DECORATE_COVER -> {
                binding.tvNextButton.text = "다음"
                supportFragmentManager.beginTransaction().replace(R.id.layout_container, ChooseCoverFragment.instance).commit()
                currentFragment = AddDiaryCurrentFragment.CHOOSE_COVER
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            pressBackButton()
            return true
        }

        return super.onKeyDown(keyCode, event)
    }
}