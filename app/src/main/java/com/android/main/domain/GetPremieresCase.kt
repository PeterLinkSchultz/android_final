package com.android.main.domain

import com.android.main.data.movie.MovieListResponse
import com.android.main.entity.Movie
import com.example.main.data.KinopoiskRepository
import javax.inject.Inject

class GetPremieresCase @Inject constructor(private val kinopoiskRepository: KinopoiskRepository) {
    suspend fun execute(): MovieListResponse {
        return kinopoiskRepository.getPremieres()
    }
}