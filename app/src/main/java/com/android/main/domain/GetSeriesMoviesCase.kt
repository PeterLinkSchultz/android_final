package com.android.main.domain

import com.android.main.data.ItemsResponse
import com.android.main.data.movie.MovieFilterParams
import com.android.main.data.movie.MovieShortDto
import com.android.main.data.movie.MovieType
import com.example.main.data.KinopoiskRepository
import javax.inject.Inject

class GetSeriesMoviesCase @Inject constructor(private val kinopoiskRepository: KinopoiskRepository) {
    suspend fun execute(page: Int): ItemsResponse<MovieShortDto> {
        return kinopoiskRepository.getMovies(object: MovieFilterParams {
            override val countries = null
            override val genres = null
            override val type = MovieType.TV_SERIES
            override val keyword = null
            override val page = page
            override val ratingFrom = null
            override val ratingTo = null
            override val yearFrom = null
            override val yearTo = null
            override val imdbId = null
        })
    }
}
