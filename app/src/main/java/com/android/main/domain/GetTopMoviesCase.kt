package com.android.main.domain

import com.android.main.data.movie.TopMoviesResponse
import com.android.main.data.movie.TopMoviesType
import com.example.main.data.KinopoiskRepository
import javax.inject.Inject

class GetTopMoviesCase @Inject constructor(private val kinopoiskRepository: KinopoiskRepository) {
    suspend fun execute(type: TopMoviesType): TopMoviesResponse {
        return kinopoiskRepository.getTopMovies(type, 1)
    }
}