package com.android.main.extensions

import android.content.Context
import android.util.DisplayMetrics

fun Int.FromDpToPx(context: Context): Int {
    return this * context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT
}