package com.android.main.domain

import com.android.main.data.ItemsResponse
import com.android.main.data.movie.MovieShortDto
import com.example.main.data.KinopoiskRepository
import javax.inject.Inject

class GetSimilarMoviesCase @Inject constructor(private val kinopoiskRepository: KinopoiskRepository) {
    suspend fun execute(movieId: Int): ItemsResponse<MovieShortDto> {
        return kinopoiskRepository.getSimilarMovies(movieId)
    }
}