package com.android.main.presentation.yearPicker

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.main.databinding.YearPickerViewBinding
import com.android.main.presentation.decorator.VerticalGridItemDecorator

class YearPickerView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding = YearPickerViewBinding.inflate(LayoutInflater.from(context))
    private var listAdapter: YearPickerGridAdapter? = null

    private val yearList = mutableListOf<Array<Int>>()

    init {
        val tempList = mutableListOf<Int>()
        for (i in YearPickerListAdapter.FROM..YearPickerListAdapter.TO) {
            tempList.add(i)
        }
        val tempArray = tempList.toIntArray()

        while(tempArray.isNotEmpty()) {
            yearList.add(tempArray.slice(0..11).toTypedArray())
        }
    }

    init {
        addView(binding.root)

        // binding.yearList.adapter = YearPickerGridAdapter(context, yearList[0])



//        with(binding.yearList) {
//            listAdapter = YearPickerListAdapter(context)
//            adapter = listAdapter
//            // layoutManager = GridLayoutManager(this@YearPickerView.context, 4, RecyclerView.HORIZONTAL, false)
//            addItemDecoration(VerticalGridItemDecorator(context, 8))
//        }

//        binding.buttonNext.setOnClickListener {
//            binding.yearList.smoothScrollToPosition(19)
//        }
//        binding.buttonPrev.setOnClickListener {
//            binding.yearList.smoothScrollToPosition(0)
//        }
    }
}