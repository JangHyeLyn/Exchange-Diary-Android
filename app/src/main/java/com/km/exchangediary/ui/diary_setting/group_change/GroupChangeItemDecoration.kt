package com.km.exchangediary.ui.diary_setting.group_change

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GroupChangeItemDecoration(private val itemSpace: Float) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (parent.getChildAdapterPosition(view) != parent.adapter?.itemCount?.minus(1)) outRect.bottom =
            itemSpace.toInt()
    }
}