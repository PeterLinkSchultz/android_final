package com.android.main.presentation.moveiList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.main.databinding.MovieCardFragmentBinding
import com.android.main.databinding.MovieMoreCardFragmentBinding
import com.android.main.entity.Movie
import com.bumptech.glide.Glide

class MovieHorizontalListAdapter(
    private val onItemClick: (movieId: Int) -> Unit,
    private val onAllClick: () -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var data: List<Movie> = listOf()

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
                    onItemClick(item.kinopoiskId)
                }
                holder.binding.movieCardTitle.text = item.nameRu
                Glide.with(holder.binding.root)
                    .load(item.posterUrlPreview)
                    .into(holder.binding.movieCardImage);
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
        return if (position != data.size - 1) TYPE_ITEM else TYPE_MORE
    }

    fun setData(newData: List<Movie>) {
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
