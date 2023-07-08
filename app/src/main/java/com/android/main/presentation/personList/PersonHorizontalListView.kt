package com.android.main.presentation.personList


import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.main.R
import com.android.main.data.staff.StaffDto
import com.android.main.databinding.MovieHorizontalListViewBinding
import com.android.main.databinding.PersonHorizontalListViewBinding
import com.android.main.entity.Movie
import com.android.main.presentation.decorator.GridItemDecorator

class PersonHorizontalListView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = PersonHorizontalListViewBinding.inflate(LayoutInflater.from(context))
    private var listAdapter: PersonHorizontalListAdapter

    init {
        binding.root.layoutParams = ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        addView(binding.root)
        listAdapter = PersonHorizontalListAdapter()

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.HorizontalListView,
            0, 0).apply {
            try {
                binding.listTitle.text = getString(R.styleable.HorizontalListView_title)
            } finally {
                recycle()
            }
        }

        with(binding.recyclerList) {
            adapter = listAdapter
            layoutManager = GridLayoutManager(this@PersonHorizontalListView.context, 4, RecyclerView.HORIZONTAL, false)

            addItemDecoration(GridItemDecorator(context, 8))
            setHasFixedSize(true)
        }
    }

    fun setData(data: List<StaffDto>, counter: Int): PersonHorizontalListView {
        listAdapter.setData(data)
        binding.buttonMore.text = counter.toString()

        return this
    }

    private fun navigateToDetail(movieId: Int) {
        findNavController().navigate(R.id.action_navigation_home_to_movieDetailFragment, bundleOf(KEY_MOVIE_ID to movieId))
    }

    private fun navigateToAll() {
        Log.i("Movie", "to all")
    }

    companion object {
        val KEY_MOVIE_ID = "movieId"
    }
}