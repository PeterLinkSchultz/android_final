package com.android.main.presentation.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.android.main.R
import com.android.main.databinding.FilterCategoryRadioButtonBinding
import com.android.main.databinding.FilterCategoryFragmentBinding

class FilterCategoryFragment: Fragment() {
    private var _binding: FilterCategoryFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FilterCategoryFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val searchViewModel = (activity as SearchController).searchViewModel

        arguments?.getString(KEY_FILTER_CATEGORY)?.let { key ->
            if (key == SearchViewModel.FILTER_KEY_COUNTRIES) {
                resources.getString(R.string.filter_county_title)
            } else {
                resources.getString(R.string.filter_genre_title)
            }.apply {
                binding.fragmentTop.setTitle(this)
            }
            if (key == SearchViewModel.FILTER_KEY_COUNTRIES) {
                resources.getString(R.string.enter_country)
            } else {
                resources.getString(R.string.enter_genre)
            }.apply {
                binding.searchText.hint = this
            }

            binding.items.let { group ->
                if (key == SearchViewModel.FILTER_KEY_COUNTRIES) {
                    searchViewModel.countries
                } else {
                    searchViewModel.genres
                }.let { items ->
                    items.forEach { (value, label) ->
                        group.addView(
                            FilterCategoryRadioButtonBinding
                                .inflate(LayoutInflater.from(context)).radioButton.apply {
                                    id = value
                                    text = label
                                    layoutParams = ViewGroup.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT,
                                    )
                                }
                        )
                    }
                    searchViewModel.getFilterValues[key]?.let {
                        group.check(it)
                    }
                    group.setOnCheckedChangeListener { _, i ->
                        searchViewModel.updateValue(key, i)
                    }
                }
            }
        }
        binding.searchText.addTextChangedListener {text ->
            val string = text.toString().lowercase()
            binding.items.children.forEach {
                val radioButton = it as RadioButton

                radioButton.visibility = if (string.isNotBlank()) {
                    if (radioButton.text.toString().lowercase().contains(string)) View.VISIBLE else View.GONE
                } else {
                    View.VISIBLE
                }
            }
        }
    }

    companion object {
        val KEY_FILTER_CATEGORY = "filterCategory"
    }
}