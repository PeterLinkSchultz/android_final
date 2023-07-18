package com.android.main.presentation.movieCategoryList

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.main.data.movie.MovieShortDto
import com.android.main.databinding.MovieCardFragmentBinding
import com.bumptech.glide.Glide

class MovieCategoryListAdapter(
    private val onItemClick: (movieId: Int) -> Unit,
): PagingDataAdapter<MovieShortDto, MovieCategoryListAdapter.MovieVerticalListHolder>(DiffUtilCallback()) {
    override fun onBindViewHolder(holder: MovieVerticalListHolder, position: Int) {
        val item = getItem(position)
        
        item?.let {
            holder.binding.root.setOnClickListener {
                (item.kinopoiskId ?: item.filmId)?.let { id -> onItemClick(id)
                    Log.i("Movie clicked", id.toString())}
            }
            holder.binding.movieCardTitle.text = item.nameRu
            Glide.with(holder.binding.root)
                .load(item.posterUrlPreview)
                .into(holder.binding.movieCardImage)
            item.ratingImdb?.let {
                holder.binding.movieCardRate.text = it.toString()
                holder.binding.movieCardRate.visibility = View.VISIBLE
            }
            holder.binding.movieCardGenre.text = item.genres?.joinToString {
                it.genre
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVerticalListHolder {
        return MovieVerticalListHolder(
            MovieCardFragmentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class MovieVerticalListHolder(val binding: MovieCardFragmentBinding): RecyclerView.ViewHolder(binding.root)

    class DiffUtilCallback: DiffUtil.ItemCallback<MovieShortDto>() {
        override fun areItemsTheSame(oldItem: MovieShortDto, newItem: MovieShortDto): Boolean {
            return (oldItem.filmId ?: oldItem.kinopoiskId) == (newItem.filmId ?: newItem.kinopoiskId)
        }

        override fun areContentsTheSame(oldItem: MovieShortDto, newItem: MovieShortDto): Boolean {
            return oldItem == newItem
        }
    }
}
