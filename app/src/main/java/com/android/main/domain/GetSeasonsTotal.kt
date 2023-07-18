package com.android.main.domain

import com.android.main.data.ItemsResponse
import com.android.main.data.movie.MovieSeriesSeasonDto
import com.example.main.data.KinopoiskRepository
import javax.inject.Inject

class GetSeasonsTotal @Inject constructor(val kinopoiskRepository: KinopoiskRepository) {
     suspend fun execute(movieId: Int): ItemsResponse<MovieSeriesSeasonDto> {
         return kinopoiskRepository.getMovieSeasons(movieId)
     }
}