package com.android.main.domain

import com.android.main.data.movie.MovieDto
import com.android.main.data.staff.StaffDto
import com.example.main.data.KinopoiskRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import javax.inject.Inject

class GetMovieCase @Inject constructor(private val kinopoiskRepository: KinopoiskRepository) {
    suspend fun execute(movieId: Int): MovieDto {
        return kinopoiskRepository.getMovie(movieId)
    }
}