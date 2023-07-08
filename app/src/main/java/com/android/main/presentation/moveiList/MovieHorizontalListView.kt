package com.android.main.presentation.moveiList

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.main.R
import com.android.main.data.movie.MovieShortDto
import com.android.main.databinding.MovieHorizontalListViewBinding
import com.android.main.presentation.decorator.HorizontalItemDecorator

class MovieHorizontalListView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = MovieHorizontalListViewBinding.inflate(LayoutInflater.from(context))
    private var listAdapter: MovieHorizontalListAdapter

    init {
        binding.root.layoutParams = ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        addView(binding.root)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.HorizontalListView,
            0, 0).apply {
            try {
                listAdapter = MovieHorizontalListAdapter({ movieId -> navigateToDetail(movieId) }, {
                    navigateToAll()
                }, getBoolean(R.styleable.HorizontalListView_hideMoreButton, false))
                binding.listTitle.text = getString(R.styleable.HorizontalListView_title)
                binding.isCountable = getBoolean(R.styleable.HorizontalListView_isCountable, false)
            } finally {
                recycle()
            }
        }

        with(binding.recyclerList) {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(this@MovieHorizontalListView.context, RecyclerView.HORIZONTAL, false)

            addItemDecoration(HorizontalItemDecorator(context, 8))
            setHasFixedSize(true)
        }
    }

    fun setData(data: List<MovieShortDto>, count: Int? = null): MovieHorizontalListView {
        listAdapter.setData(data)

        count?.let {
            binding.buttonMore.text = count.toString()
        }

        return this
    }

    private fun navigateToDetail(movieId: Int) {
        findNavController().navigate(R.id.action_navigation_home_to_movieDetailFragment, bundleOf(
            KEY_MOVIE_ID to movieId))
    }

    private fun navigateToAll() {
        Log.i("Movie", "to all")
    }

    companion object {
        val KEY_MOVIE_ID = "movieId"
    }
}