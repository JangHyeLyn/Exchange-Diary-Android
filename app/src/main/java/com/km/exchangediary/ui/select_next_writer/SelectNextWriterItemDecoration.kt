package com.km.exchangediary.ui.select_next_writer

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SelectNextWriterItemDecoration(private val itemSpace: Float) : RecyclerView.ItemDecoration() {
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