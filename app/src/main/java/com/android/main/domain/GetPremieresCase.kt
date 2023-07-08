package com.android.main.domain

import com.android.main.data.ItemsResponse
import com.android.main.data.movie.MovieShortDto
import com.example.main.data.KinopoiskRepository
import javax.inject.Inject

class GetPremieresCase @Inject constructor(private val kinopoiskRepository: KinopoiskRepository) {
    suspend fun execute(): ItemsResponse<MovieShortDto> {
        return kinopoiskRepository.getPremieres()
    }
}