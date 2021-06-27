package com.km.exchangediary.ui.select_next_writer

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseActivity
import com.km.exchangediary.databinding.ActivitySelectNextWriterBinding
import com.km.exchangediary.utils.toPx
import kotlinx.android.synthetic.main.activity_select_next_writer.*

class SelectNextWriterActivity : BaseActivity<ActivitySelectNextWriterBinding>() {
    override fun layoutRes(): Int = R.layout.activity_select_next_writer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBindView()
    }

    private fun onBindView() {
        binding.rvNextWriterList.apply {
            adapter = NextWriterRecyclerViewAdapter()
            layoutManager = GridLayoutManager(
                this@SelectNextWriterActivity,
                2
            )
        }

        binding.rvNextWriterList.addItemDecoration(SelectNextWriterItemDecoration(8.toPx().toFloat()))
    }
}