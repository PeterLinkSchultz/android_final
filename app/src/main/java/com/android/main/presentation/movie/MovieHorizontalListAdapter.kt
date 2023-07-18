package com.android.main.presentation.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.main.data.movie.MovieShortDto
import com.android.main.databinding.MovieCardFragmentBinding
import com.android.main.databinding.MovieMoreCardFragmentBinding
import com.bumptech.glide.Glide

class MovieHorizontalListAdapter(
    private val onItemClick: (movieId: Int) -> Unit,
    private val onAllClick: () -> Unit,
    private val hideMoreButton: Boolean
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var data: List<MovieShortDto> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return if (viewType == TYPE_ITEM) {
            MovieHorizontalListViewHolder(MovieCardFragmentBinding.inflate(LayoutInflater.from(parent.context)))
        } else {
            MovieHorizontalMoreViewHolder(MovieMoreCardFragmentBinding.inflate(LayoutInflater.from(parent.context)))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieHorizontalListViewHolder -> {
                val item = data[position]

                holder.binding.root.setOnClickListener {
                    (item.kinopoiskId ?: item.filmId)?.let { id -> onItemClick(id) }
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
            is MovieHorizontalMoreViewHolder -> {
                holder.binding.buttonMore.setOnClickListener {
                    onAllClick()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (hideMoreButton || position != data.size - 1) TYPE_ITEM else TYPE_MORE
    }

    fun setData(newData: List<MovieShortDto>) {
        data = newData

        notifyDataSetChanged()
    }

    companion object {
        private const val TYPE_ITEM: Int = 1
        private const val TYPE_MORE: Int = 2
    }

    class MovieHorizontalListViewHolder(val binding: MovieCardFragmentBinding): RecyclerView.ViewHolder(binding.root)
    class MovieHorizontalMoreViewHolder(val binding: MovieMoreCardFragmentBinding): RecyclerView.ViewHolder(binding.root)
}
