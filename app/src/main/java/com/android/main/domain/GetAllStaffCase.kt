package com.android.main.domain

import com.android.main.data.staff.StaffDto
import com.example.main.data.KinopoiskRepository
import javax.inject.Inject

class GetAllStaffCase @Inject constructor(private val kinopoiskRepository: KinopoiskRepository) {
    suspend fun execute(movieId: Int): List<StaffDto> {
        return kinopoiskRepository.getStaffByMovieId(movieId)
    }
}