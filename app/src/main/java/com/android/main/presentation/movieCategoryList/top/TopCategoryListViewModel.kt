package com.android.main.presentation.movieCategoryList.top

import com.android.main.data.movie.paging.PopularMoviesPagingSource
import com.android.main.presentation.movieCategoryList.MovieCategoryListViewModel
import javax.inject.Inject

class TopCategoryListViewModel @Inject constructor(topPagingSource: PopularMoviesPagingSource): MovieCategoryListViewModel(topPagingSource)