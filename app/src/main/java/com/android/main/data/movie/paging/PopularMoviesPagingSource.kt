package com.android.main.data.movie.paging

import com.android.main.data.movie.MovieShortDto
import com.android.main.data.movie.TopMoviesType
import com.example.main.data.KinopoiskRepository
import javax.inject.Inject

class PopularMoviesPagingSource @Inject constructor(val kinopoiskRepository: KinopoiskRepository): MoviePagingSource() {
    override suspend fun getList(page: Int): List<MovieShortDto> {
        return kinopoiskRepository.getTopMovies(TopMoviesType.TOP_100_POPULAR_FILMS, page).films
    }
}