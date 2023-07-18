package com.android.main.data.movie.paging

import com.android.main.data.movie.MovieShortDto
import com.android.main.domain.GetSeriesMoviesCase
import javax.inject.Inject

class SeriesMoviesPagingSource @Inject constructor(val getSeriesMoviesCase: GetSeriesMoviesCase): MoviePagingSource() {
    override suspend fun getList(page: Int): List<MovieShortDto> {
        return getSeriesMoviesCase.execute(page).items
    }
}