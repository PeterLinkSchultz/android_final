package com.android.main.presentation.yearPicker

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.main.databinding.YearPickerGridViewBinding
import com.android.main.databinding.YearPickerItemViewBinding

class YearPickerListAdapter(val context: Context): RecyclerView.Adapter<YearPickerListAdapter.ViewHolder>() {

    private val yearList = mutableListOf<Array<Int>>()

    init {
        val tempList = mutableListOf<Int>()
        for (i in FROM..TO) {
            tempList.add(i)
        }
        val tempArray = tempList.toIntArray()

        while(tempArray.isNotEmpty()) {
            yearList.add(tempArray.slice(0..15).toTypedArray())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(YearPickerGridViewBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount() = yearList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = yearList[position]
        val viewBinding = YearPickerItemViewBinding.inflate(LayoutInflater.from(context))
        holder.binding.gridView.adapter = YearPickerGridAdapter(context, items, viewBinding)
        // holder.binding.year.text = yearList[position].toString()
    }

    companion object {
        val FROM = 1960
        val TO = 2022
    }

    class ViewHolder(val binding: YearPickerGridViewBinding): RecyclerView.ViewHolder(binding.root)
}