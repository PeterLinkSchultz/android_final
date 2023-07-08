package com.android.main.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import com.android.main.R
import com.android.main.databinding.FragmentTopViewBinding

class FragmentTopView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = FragmentTopViewBinding.inflate(LayoutInflater.from(context))

    init {
        binding.root.layoutParams = ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        addView(binding.root)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.HorizontalListView,
            0, 0).apply {
            try {
                binding.topTitle.text = getString(R.styleable.HorizontalListView_title)
            } finally {
                recycle()
            }
        }
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}