package com.android.main.presentation.personList

import android.content.Context
import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class PersonItemOffsetDecorator(private val context: Context): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val offset = 8.FromDpToPx(context)

        with(outRect) {
            right = offset
            bottom = offset
        }
    }

    private fun Int.FromDpToPx(context: Context): Int {
        return this * context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT
    }
}