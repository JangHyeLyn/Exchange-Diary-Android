package com.km.exchangediary.ui.select_next_writer

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseActivity
import com.km.exchangediary.databinding.ActivitySelectNextWriterBinding
import kotlinx.android.synthetic.main.activity_select_next_writer.*

class SelectNextWriterActivity : BaseActivity<ActivitySelectNextWriterBinding>() {
    override fun layoutRes(): Int = R.layout.activity_select_next_writer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onBindView()

    }

    private fun onBindView() {
        binding.rvNextWriterList.apply {
            adapter = NextWriterListAdapter()
            layoutManager = GridLayoutManager(
                this@SelectNextWriterActivity,
                2
            )
        }
        rv_next_writer_list.addItemDecoration(SpacingRecyclerView(dpToPixel(8.toFloat())))
    }

    class SpacingRecyclerView(private val itemSpace: Float) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)

            val layoutParams = view.layoutParams as GridLayoutManager.LayoutParams
            val spanIndex = layoutParams.spanIndex

            if (spanIndex == 0) outRect.right = itemSpace.toInt()

            outRect.bottom = itemSpace.toInt()
        }
    }

    private fun Context.dpToPixel(dp: Float): Float {
        return dp * (resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }
}