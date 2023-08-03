package com.android.main.presentation.textNumRadioGroup

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.IdRes
import androidx.core.view.children
import androidx.core.view.marginEnd
import com.android.main.extensions.FromDpToPx

class TextNumRadioGroup
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var protectFromCheckedChange = false
    private var checkedId = View.NO_ID
    val checked get() = checkedId

    private var childOnCheckedChangeListener: TextNumRadioButton.OnCheckedChangeListener? = null
    private var onChangeListener: (id: Int) -> Unit = { }

    init {
        childOnCheckedChangeListener = CheckedStateTracker()
    }

    private fun setCheckedId(@IdRes id: Int) {
        checkedId = id
        onChangeListener(id)
    }

    private fun changeChecked(id: Int, isChecked: Boolean) {
        (children.find {
            it.id == id
        } as TextNumRadioButton).isChecked = isChecked
    }

    private inner class CheckedStateTracker : TextNumRadioButton.OnCheckedChangeListener {
        override fun onCheckedChanged(id: Int, isChecked: Boolean) {
            if (protectFromCheckedChange) {
                return
            }
            protectFromCheckedChange = true
            if (checkedId != View.NO_ID) {
                changeChecked(checkedId, false)
            }
            protectFromCheckedChange = false

            setCheckedId(id)
        }
    }

    fun setOnChangeListener(listener: (id: Int) -> Unit) {
        onChangeListener = listener
    }

    fun setChecked(id: Int) {
        changeChecked(id, true)
    }

    override fun addView(child: View?) {
        child?.let {
            it.layoutParams = ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                val margin = (4).FromDpToPx(context)

                this.marginStart = margin
                this.marginEnd = margin
            }
        }
        super.addView(child)
    }

    override fun onViewAdded(child: View?) {
        if (child is TextNumRadioButton) {
            childOnCheckedChangeListener?.let {
                child.addOnCheckChangeListener(it)
            }
        }

        super.onViewAdded(child)
    }

    companion object {
        fun defaultOnChangeListener(id: Int) {}
    }
}