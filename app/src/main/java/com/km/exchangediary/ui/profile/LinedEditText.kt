package com.km.exchangediary.ui.profile

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.km.exchangediary.R


class LinedEditText(context: Context?, attrs: AttributeSet?) :
    androidx.appcompat.widget.AppCompatEditText(context!!, attrs) {
    private val mRect: Rect = Rect()
    private val mPaint: Paint = Paint()

    override fun onDraw(canvas: Canvas) {
        var curHeight = 0
        val r = mRect
        val paint = mPaint
        val baseline = getLineBounds(0, r)
        curHeight = baseline + 15
        while (curHeight < height) {
            canvas.drawLine(r.left.toFloat(), curHeight.toFloat(),
                r.right.toFloat(), curHeight.toFloat(), paint)
            curHeight += lineHeight
        }
        super.onDraw(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        invalidate()
    }

    init {
        mPaint.apply {
            style = Paint.Style.STROKE
            color = ContextCompat.getColor(context!!, R.color.profile_line_color)
        }
    }
}