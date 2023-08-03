package com.android.main.presentation.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.android.main.R
import com.android.main.data.movie.MovieShortDto
import com.android.main.databinding.SearchFragmentBinding
import com.android.main.di.DaggerAppComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchFragment: Fragment() {

//    private var _binding: SearchFragmentBinding? = null
//    private val binding get() = _binding!!
//
//    class ViewModelFactory @Inject constructor(viewModel: SearchViewModel):
//        com.android.main.presentation.ViewModelFactory(viewModel)
//    private val viewModel: SearchViewModel by viewModels {
//        DaggerAppComponent.create().searchViewModelFactory()
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = SearchFragmentBinding.inflate(inflater, container, false)
//
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        childFragmentManager.commit {
//            replace(R.id.childFragment, SearchListFragment())
//        }
//    }
//
//    override fun updateSearchList() {
//        // viewModel.pagedMovies
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//
//        _binding = null
//    }
//
//    override fun getPagedFlow(): Flow<PagingData<MovieShortDto>> = viewModel.pagedMovies
//    override fun getLoading(): LiveData<Boolean> = viewModel.loading
//    override fun getValues(): MutableMap<String, Int> = viewModel.filterValues
}