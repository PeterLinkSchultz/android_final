package com.android.main.presentation.movieCategoryList.serires

import com.android.main.data.movie.paging.SeriesMoviesPagingSource
import com.android.main.presentation.movieCategoryList.MovieCategoryListViewModel
import javax.inject.Inject

class SeriesCategoryListViewModel @Inject constructor(pagingSource: SeriesMoviesPagingSource): MovieCategoryListViewModel(pagingSource)