package com.android.main.presentation.movieDetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.android.main.data.movie.MovieDto
import com.android.main.data.staff.Profession
import com.android.main.databinding.MovieDetailFragmentBinding
import com.android.main.di.DaggerAppComponent
import com.android.main.entity.Movie
import com.android.main.presentation.LoadDataState
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch

class MovieDetailFragment: Fragment() {

    private var _binding: MovieDetailFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieDetailViewModel by viewModels {
        DaggerAppComponent.create().movieDetailViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MovieDetailFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val movieId = arguments?.getInt("movieId")
        Log.i("Movie id is passed", movieId.toString())
        if (movieId != null) {
            viewModel.loadData(movieId)
        }
        viewModel.movieData.observe(viewLifecycleOwner) {
            when (it) {
                is LoadDataState.Loading -> binding.loading.visibility = View.VISIBLE
                is LoadDataState.Failure -> {}
                is LoadDataState.Success -> {
                    binding.loading.visibility = View.GONE
                    bindData(it.model)
                }
            }
        }
    }

    private fun bindData(movie: MovieDetailViewModel.MovieInfo) {
        Glide.with(binding.root)
            .load(movie.movieDetail.posterUrl)
            .into(binding.moviePoster);
        binding.movieDesc.text = movie.movieDetail.description
        binding.movieShortDesc.text = movie.movieDetail.shortDescription

        movie.staffList.filter {
            it.professionKey == Profession.ACTOR
        }.apply {
            binding.movieCast.setData(this, this.size)
        }
        movie.staffList.filter {
            it.professionKey != Profession.ACTOR
        }.apply {
            binding.movieStaff.setData(this, this.size)
        }
    }
}