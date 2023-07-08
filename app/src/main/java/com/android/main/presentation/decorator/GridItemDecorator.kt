package com.android.main.presentation.decorator

import android.content.Context
import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridItemDecorator(private val context: Context, private val size: Int): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val offset = size.FromDpToPx(context)

        with(outRect) {
            right = offset
            bottom = offset
        }
    }

    private fun Int.FromDpToPx(context: Context): Int {
        return this * context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT
    }
}