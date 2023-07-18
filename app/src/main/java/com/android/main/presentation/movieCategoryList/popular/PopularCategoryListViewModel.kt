package com.android.main.presentation.movieCategoryList.popular

import com.android.main.data.movie.paging.PopularMoviesPagingSource
import com.android.main.presentation.movieCategoryList.MovieCategoryListViewModel
import javax.inject.Inject

class PopularCategoryListViewModel @Inject constructor(pagingSource: PopularMoviesPagingSource): MovieCategoryListViewModel(pagingSource)