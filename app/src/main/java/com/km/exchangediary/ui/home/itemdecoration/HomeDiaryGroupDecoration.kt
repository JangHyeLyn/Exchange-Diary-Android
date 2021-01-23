package com.km.exchangediary.ui.home.itemdecoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.km.exchangediary.utils.toPx

class HomeDiaryGroupDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view) // item position

        /* TODO: 공식이 있는듯 하다.
            공식 잘 생각해서 해보자..
            현재는 임시 */
        val COUNT = 3
        val column: Int = position % COUNT // item column

        outRect.left = 20.toPx() - column * 20.toPx() / COUNT // spacing - column * ((1f / spanCount) * spacing)
        outRect.right = (column + 1) * 20.toPx() / COUNT // (column + 1) * ((1f / spanCount) * spacing)
        if (position < COUNT) { // top edge
            outRect.top = 15.toPx()
        }
        outRect.bottom = 15.toPx() // item bottom
    }
}