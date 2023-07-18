package com.android.main.presentation.movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.core.view.marginEnd
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.android.main.R
import com.android.main.data.movie.MovieSeriesEpisodeDto
import com.android.main.databinding.RoundedRadioButtonBinding
import com.android.main.databinding.SeriesDetailFragmentBinding
import com.android.main.di.DaggerAppComponent
import com.android.main.extensions.FromDpToPx
import com.android.main.presentation.LoadDataState
import com.android.main.presentation.ViewModelFactory
import com.android.main.presentation.movie.MovieDetailFragment.Companion.KEY_MOVIE_ID
import com.android.main.presentation.movie.MovieDetailFragment.Companion.KEY_MOVIE_TITLE
import javax.inject.Inject

class SeriesDetailFragment: Fragment() {

    private var _binding: SeriesDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private val FIRST_SEASON_ID = 1
    private lateinit var listAdapter: SeriesListAdapter

    class VieModelFactory @Inject constructor(viewModel: SeriesViewModel): ViewModelFactory(viewModel)

    private val viewModel: SeriesViewModel by viewModels {
        DaggerAppComponent.create().seriesViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SeriesDetailFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerList.apply {
            listAdapter = SeriesListAdapter(resources)
            adapter = listAdapter

            layoutManager = LinearLayoutManager(this@SeriesDetailFragment.context)
            setHasFixedSize(true)
        }

        viewModel.data.observe(viewLifecycleOwner) {
            Log.i("observe", it.toString())
            when(it) {
                is LoadDataState.Loading -> {}
                is LoadDataState.Failure -> {}
                is LoadDataState.Success -> {
                    createRadioButtons(it.model.total)
                    bindData(FIRST_SEASON_ID, it.model.items[FIRST_SEASON_ID - 1].episodes)
                }
            }
        }

        arguments?.let {
            it.getString(KEY_MOVIE_TITLE)?.let { title ->
                binding.fragmentTop.setTitle(title)
            }
            it.getInt(KEY_MOVIE_ID).let { id->
                viewModel.loadData(id)
            }
        }
    }

    private fun bindData(season: Int, episodes: List<MovieSeriesEpisodeDto>) {
        listAdapter.setData(episodes)

        binding.seasonInfoTitle.text = resources.getString(
            R.string.series_title,
            season,
            resources.getQuantityString(R.plurals.episodes_counter, episodes.size, episodes.size)
        )
    }

    private fun createRadioButtons(size: Int) {
        repeat(size) {
            val id = it + 1
            binding.buttonsSeasons.addView(RoundedRadioButtonBinding.inflate(LayoutInflater.from(context)).radioButton.apply {
                this.text = id.toString()
                this.id = id
                layoutParams = ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    this.marginEnd = (8).FromDpToPx(context)
                }
                Log.i("Radio margin", this.marginEnd.toString())
            })
        }
        binding.buttonsSeasons.check(FIRST_SEASON_ID)
        binding.buttonsSeasons.setOnCheckedChangeListener { radioGroup, i ->
            bindData(i, (viewModel.data.value as LoadDataState.Success).model.items[i - 1].episodes)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
