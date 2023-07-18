package com.android.main.presentation.movieCategoryList.usaAction

import com.android.main.data.movie.paging.UsaActionMoviesPagingSource
import com.android.main.presentation.movieCategoryList.MovieCategoryListViewModel
import javax.inject.Inject

class UsaActionCategoryListViewModel @Inject constructor(pagingSource: UsaActionMoviesPagingSource): MovieCategoryListViewModel(pagingSource)