package com.android.main.presentation.movieCategoryList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.android.main.databinding.TopCategoryListFragmentBinding
import com.android.main.presentation.decorator.VerticalGridItemDecorator
import com.android.main.presentation.movie.MovieHorizontalListView
import com.android.main.presentation.movie.MovieDetailFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

abstract class CategoryListFragment: Fragment() {
    private var _binding: TopCategoryListFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var listAdapter: MovieCategoryListAdapter

    abstract var navDetailId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TopCategoryListFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let{
            it.getString(MovieHorizontalListView.KEY_TITLE)?.let { title ->
                binding.fragmentTop.setTitle(title)
            }
        }


        with(binding.recyclerList) {
            listAdapter = MovieCategoryListAdapter {
                findNavController().navigate(
                    navDetailId,
                    bundleOf(MovieDetailFragment.KEY_MOVIE_ID to it)
                )
            }
            adapter = listAdapter
            layoutManager = GridLayoutManager(this@CategoryListFragment.context, 2)
            addItemDecoration(VerticalGridItemDecorator(context, 16))
            setHasFixedSize(true)
        }

        getViewModel().pagedMovies.onEach {
                Log.i("Movie top", it.toString())
                listAdapter.submitData(it)
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    abstract fun getViewModel(): MovieCategoryListViewModel
}