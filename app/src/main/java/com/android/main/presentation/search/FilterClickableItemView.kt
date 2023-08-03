package com.android.main.presentation.search

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import com.android.main.R
import com.android.main.databinding.FilterClickableItemViewBinding

@BindingMethods(
    value = [
        BindingMethod(
            type = FilterClickableItemView::class,
            attribute = "value",
            method = "setValue"
        )
    ]
)
class FilterClickableItemView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = FilterClickableItemViewBinding.inflate(LayoutInflater.from(context))

    init {
        binding.root.layoutParams = ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        addView(binding.root)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.FilterClickableItemView,
            0, 0).apply {
            try {
                 binding.name.text = getString(R.styleable.FilterClickableItemView_name)
            } finally {
                recycle()
            }
        }
    }

    fun setClickListener(listener: () -> Unit) {
        binding.root.setOnClickListener {
            Log.i("Filter", "clicked")
            listener()
        }
    }

    fun setValue(string: String) {
        binding.value.text = string
    }
}