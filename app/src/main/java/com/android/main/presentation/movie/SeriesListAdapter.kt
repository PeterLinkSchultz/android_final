package com.android.main.presentation.movie

import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.main.R
import com.android.main.data.movie.MovieSeriesEpisodeDto
import com.android.main.databinding.SeriesListItemFragmentBinding

class SeriesListAdapter(private val resources: Resources): RecyclerView.Adapter<SeriesListAdapter.Holder>() {
    private var data = listOf<MovieSeriesEpisodeDto>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(SeriesListItemFragmentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        ))
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = data[position]

        holder.binding.apply {
            Log.i("Release", item.releaseDate.toString())
            item.releaseDate?.split('-')?.let {
                seriesDate.text = resources.getString(
                    R.string.release_date,
                        it[2],
                        resources.getStringArray(R.array.release_month)[it[1].toInt() - 1],
                        it[0],
                    )
            }
            seriesName.text = resources.getString(R.string.episode_title, item.episodeNumber, item.nameRu ?: item.nameEn)
        }
    }

    fun setData(newData: List<MovieSeriesEpisodeDto>) {
        data = newData

        notifyDataSetChanged()
    }

    class Holder(val binding: SeriesListItemFragmentBinding): RecyclerView.ViewHolder(binding.root)
}