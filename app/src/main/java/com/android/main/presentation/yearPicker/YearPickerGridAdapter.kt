package com.android.main.presentation.yearPicker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.android.main.databinding.YearPickerItemViewBinding

class YearPickerGridAdapter(context: Context, val list: Array<Int>, val binding: YearPickerItemViewBinding):
    ArrayAdapter<Int>(context, 0, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        binding.year.text = list[position].toString()

        return binding.root
    }
}