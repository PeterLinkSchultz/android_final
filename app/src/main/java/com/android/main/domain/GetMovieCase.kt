package com.android.main.domain

import com.android.main.data.movie.MovieDto
import com.example.main.data.KinopoiskRepository
import javax.inject.Inject

class GetMovieCase @Inject constructor(private val kinopoiskRepository: KinopoiskRepository) {
    suspend fun execute(movieId: Int): MovieDto {
        return kinopoiskRepository.getMovie(movieId)
    }
}