package com.android.main.presentation.decorator

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.android.main.extensions.FromDpToPx

class HorizontalItemDecorator(private val context: Context, private val size: Int): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val offset = size.FromDpToPx(context)

        with(outRect) {
            right = offset
        }
    }
}