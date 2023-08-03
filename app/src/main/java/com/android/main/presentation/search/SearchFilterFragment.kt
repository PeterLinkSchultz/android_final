package com.android.main.presentation.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.main.R
import com.android.main.data.movie.MovieType
import com.android.main.databinding.SearchFilterFragmentBinding
import java.lang.StringBuilder

class SearchFilterFragment: Fragment() {

    private var _binding: SearchFilterFragmentBinding? = null
    private val binding get() = _binding!!
    private val idToType = mapOf(
        R.id.typeMovie to MovieType.FILM.ordinal,
        R.id.typeAll to MovieType.ALL.ordinal,
        R.id.typeSerial to MovieType.TV_SERIES.ordinal,
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchFilterFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val searchViewModel = (activity as SearchController).searchViewModel
        val values = searchViewModel.getFilterValues
        val keys = SearchViewModel.Companion

        binding.viewModel = values
        binding.keys = keys
        binding.countries = searchViewModel.countries
        binding.genres = searchViewModel.genres

        val dateFrom = values[keys.FILTER_KEY_YEAR_FROM]
        val dateTo = values[keys.FILTER_KEY_YEAR_TO]

        val period = StringBuilder("")
        if (dateFrom != null) {
            period.append(resources.getString(R.string.filter_period_from, dateFrom))
        }
        if (dateTo != null) {
            period.append(resources.getString(R.string.filter_period_to, dateTo))
        }
        binding.period = period.toString()

        binding.typesGroup.setOnCheckedChangeListener { radioGroup, id ->
            values[keys.FILTER_KEY_TYPE] = idToType[id] as Int
        }
        binding.filterCountry.setClickListener {
            findNavController().navigate(
                R.id.action_searchFilterFragment_to_filterCategoryFragment,
                bundleOf(FilterCategoryFragment.KEY_FILTER_CATEGORY to SearchViewModel.FILTER_KEY_COUNTRIES)
            )
        }
        binding.filterGenre.setClickListener {
            findNavController().navigate(
                R.id.action_searchFilterFragment_to_filterCategoryFragment,
                bundleOf(FilterCategoryFragment.KEY_FILTER_CATEGORY to SearchViewModel.FILTER_KEY_GENRES)
            )
        }
        binding.filterPeriod.setClickListener {
            findNavController().navigate(R.id.action_searchFilterFragment_to_filterPeriodFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}