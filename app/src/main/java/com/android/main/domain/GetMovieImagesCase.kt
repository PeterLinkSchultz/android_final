package com.android.main.domain

import com.android.main.data.ItemsResponse
import com.android.main.data.image.GalleryImageDto
import com.example.main.data.KinopoiskRepository
import javax.inject.Inject

class GetMovieImagesCase @Inject constructor(private val kinopoiskRepository: KinopoiskRepository) {
    suspend fun execute(movieId: Int, page: Int): ItemsResponse<GalleryImageDto> {
        return kinopoiskRepository.getMovieImages(movieId, page)
    }
}