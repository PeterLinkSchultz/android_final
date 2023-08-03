package com.android.main.presentation.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.main.data.movie.MovieShortDto
import com.android.main.databinding.MovieSearchItemFragmentBinding
import com.bumptech.glide.Glide

class SearchListAdapter(
    val onClickItem: (filmId: Int) -> Unit
): PagingDataAdapter<MovieShortDto, SearchListAdapter.ViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MovieSearchItemFragmentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movieItem = getItem(position)

        movieItem?.let {
            val id = movieItem.filmId ?: movieItem.kinopoiskId
            val rating = movieItem.rating ?: movieItem.ratingImdb

            holder.viewBinding.root.setOnClickListener {
                onClickItem(id ?: 0)
            }

            holder.viewBinding.movieName.text = movieItem.nameRu ?: movieItem.nameEn ?: movieItem.nameOriginal
            rating?.let {
                holder.viewBinding.movieRating.apply {
                    text = it.toString()
                    visibility = View.VISIBLE
                }
            }
            val description = mutableListOf<String>()
            movieItem.year?.let { year ->
                description.add(year.toString())
            }
            movieItem.genres?.forEach {
                description.add(it.genre)
            }
            description.joinToString(", ").let {
                if (it.isNotBlank()) {
                    holder.viewBinding.movieDetail.apply {
                        this.text = it
                        this.visibility = View.VISIBLE
                    }
                }
            }

            Glide.with(holder.viewBinding.root)
                .load(movieItem.posterUrlPreview)
                .into(holder.viewBinding.moviePoster);
        }
    }

    class ViewHolder(val viewBinding: MovieSearchItemFragmentBinding):
        RecyclerView.ViewHolder(viewBinding.root)

    class DiffUtilCallback: DiffUtil.ItemCallback<MovieShortDto>() {
        override fun areItemsTheSame(oldItem: MovieShortDto, newItem: MovieShortDto): Boolean {
            val oldId = oldItem.filmId ?: oldItem.kinopoiskId
            val newId = newItem.filmId ?: newItem.kinopoiskId

            return oldId == newId
        }

        override fun areContentsTheSame(oldItem: MovieShortDto, newItem: MovieShortDto): Boolean {
            return oldItem == newItem
        }
    }
}