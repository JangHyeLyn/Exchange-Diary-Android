package com.km.exchangediary.ui.home.itemdecoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.km.exchangediary.utils.toPx

class HomeWritingDiaryDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)

        if (position == 0) {
            outRect.left = 20.toPx()
        } else {
            outRect.left = 12.toPx()
        }
    }
}