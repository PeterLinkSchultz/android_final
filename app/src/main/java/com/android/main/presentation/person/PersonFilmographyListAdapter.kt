package com.android.main.presentation.person

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.main.data.staff.StaffMovieDto
import com.android.main.databinding.MovieSearchItemFragmentBinding

class PersonFilmographyListAdapter(
    val onClickItem: (filmId: Int) -> Unit
): RecyclerView.Adapter<PersonFilmographyListAdapter.ViewHolder>() {

    private var data = listOf<StaffMovieDto>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MovieSearchItemFragmentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

        holder.viewBinding.root.setOnClickListener {
            onClickItem(item.filmId)
        }
        holder.viewBinding.movieName.text = item.nameRu ?: item.nameEn
        item.rating?.let {
            holder.viewBinding.movieRating.apply {
                text = it.toString()
                visibility = View.VISIBLE
            }
        }
    }

    fun setData(newData: List<StaffMovieDto>) {
        data = newData

        notifyDataSetChanged()
    }

    class ViewHolder(val viewBinding: MovieSearchItemFragmentBinding):
        RecyclerView.ViewHolder(viewBinding.root)
}