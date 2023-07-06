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
import com.android.main.databinding.MovieHorizontalListViewBinding
import com.android.main.entity.Movie
import com.android.main.presentation.ItemOffsetDecorator

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
        listAdapter = MovieHorizontalListAdapter({ movieId -> navigateToDetail(movieId) }, {
            navigateToAll()
        })

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.HorizontalListView,
            0, 0).apply {
            try {
                Log.i("List view: title", getString(R.styleable.HorizontalListView_title).toString())
                binding.listTitle.text = getString(R.styleable.HorizontalListView_title)
                Log.i("List view: isCountable", getBoolean(R.styleable.HorizontalListView_isCountable, false).toString())
                binding.isCountable = getBoolean(R.styleable.HorizontalListView_isCountable, false)
            } finally {
                recycle()
            }
        }

        with(binding.recyclerList) {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(this@MovieHorizontalListView.context, RecyclerView.HORIZONTAL, false)

            addItemDecoration(ItemOffsetDecorator(context))
            setHasFixedSize(true)
        }
    }

    fun setData(data: List<Movie>): MovieHorizontalListView {
        listAdapter.setData(data)

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