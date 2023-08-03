package com.android.main.presentation.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.asFlow
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.android.main.R
import com.android.main.databinding.SearchListFragmentBinding
import com.android.main.presentation.decorator.VerticalItemDecorator
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SearchListFragment: Fragment() {

    private var _binding: SearchListFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var listAdapter: SearchListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchListFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val searchViewModel = (activity as SearchController).searchViewModel

        with(binding.recyclerList) {
            listAdapter = SearchListAdapter {  }
            adapter = listAdapter
            layoutManager = LinearLayoutManager(this@SearchListFragment.context, VERTICAL, false)
            setHasFixedSize(true)
            addItemDecoration(VerticalItemDecorator(context, 8))
        }

        searchViewModel.loading.asFlow().onEach {
             Log.i("Search loading", it.toString())
             binding.progress.visibility = if (it) View.VISIBLE else View.GONE
        }.launchIn(viewLifecycleOwner.lifecycleScope)
        searchViewModel.pagedMovies.onEach {
            Log.i("Search result", it.toString())
            listAdapter.submitData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        binding.openFilterButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_search_to_searchFilterFragment)
        }
        searchViewModel.pagedMovies
        listAdapter.refresh()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}