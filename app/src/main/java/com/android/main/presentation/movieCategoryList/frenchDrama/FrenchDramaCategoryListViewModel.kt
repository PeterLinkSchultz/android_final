package com.android.main.presentation.movieCategoryList.frenchDrama

import com.android.main.data.movie.paging.FrenchDramaMoviesPagingSource
import com.android.main.presentation.movieCategoryList.MovieCategoryListViewModel
import javax.inject.Inject

class FrenchDramaCategoryListViewModel @Inject constructor(pagingSource: FrenchDramaMoviesPagingSource): MovieCategoryListViewModel(pagingSource)