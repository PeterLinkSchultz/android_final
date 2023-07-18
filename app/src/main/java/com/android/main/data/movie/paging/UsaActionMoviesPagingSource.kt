package com.android.main.data.movie.paging

import com.android.main.data.movie.MovieShortDto
import com.android.main.domain.GetCountryGenreMoviesCase
import javax.inject.Inject

class UsaActionMoviesPagingSource @Inject constructor(val getCountryGenreMoviesCase: GetCountryGenreMoviesCase): MoviePagingSource() {
    override suspend fun getList(page: Int): List<MovieShortDto> {
        return getCountryGenreMoviesCase.execute(1, 11, page).items
    }
}