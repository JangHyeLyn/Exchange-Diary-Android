package com.km.exchangediary.utils

import android.util.TypedValue
import com.km.exchangediary.application.App

fun Int.toPx() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), App.app.resources.displayMetrics).toInt()