package com.android.main.presentation.movieCategoryList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.main.data.movie.MovieShortDto
import com.android.main.data.movie.paging.MoviePagingSource
import kotlinx.coroutines.flow.Flow

open class MovieCategoryListViewModel(val pagingSource: MoviePagingSource): ViewModel() {
    val pagedMovies: Flow<PagingData<MovieShortDto>> = Pager(
        config = PagingConfig(pageSize =  20),
        pagingSourceFactory = { pagingSource }
    ).flow.cachedIn(viewModelScope)
}