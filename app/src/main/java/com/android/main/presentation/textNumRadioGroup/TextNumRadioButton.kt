package com.android.main.presentation.textNumRadioGroup

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Checkable
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.children
import com.android.main.R
import com.android.main.databinding.TextNumRadioButtonViewBinding

class TextNumRadioButton@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): LinearLayout(context, attrs, defStyleAttr), Checkable {

    private val binding = TextNumRadioButtonViewBinding.inflate(LayoutInflater.from(context))
    private var checked: Boolean = false

    interface OnCheckedChangeListener {
        fun onCheckedChanged(id: Int, isChecked: Boolean)
    }

    private val listeners = mutableListOf<OnCheckedChangeListener>()

    init {
        addView(binding.root)
        background = ResourcesCompat.getDrawable(resources, R.drawable.rounded_radio_button, null)
        isClickable = true
    }

    fun setContent(text: String, num: Int) {
        binding.text.text = text
        binding.number.text = num.toString()
    }

    override fun setChecked(updated: Boolean) {
        if (checked != updated) {
            checked = updated
            listeners.forEach { it.onCheckedChanged(id, checked) }
            binding.text.isChecked = checked
            refreshDrawableState()
        }
    }

    override fun isChecked(): Boolean {
        return checked
    }

    override fun toggle() {
        isChecked = !checked
    }

    override fun performClick(): Boolean {
        if (checked) return false
        toggle()
        return super.performClick()
    }

    fun addOnCheckChangeListener(onCheckedChangeListener: OnCheckedChangeListener) {
        listeners.add(onCheckedChangeListener)
    }

    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        val drawableState = super.onCreateDrawableState(extraSpace + 1)
        if (isChecked) {
            View.mergeDrawableStates(drawableState, CHECKED_STATE_SET)
        }
        return drawableState
    }

    companion object {
        private val CHECKED_STATE_SET = intArrayOf(android.R.attr.state_checked)
    }
}