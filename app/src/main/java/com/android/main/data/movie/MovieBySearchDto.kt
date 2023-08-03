package com.android.main.data.movie

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieBySearchDto(
    val kinopoiskId: Int,
    val ratingKinopoisk: Double?,
    override val nameRu: String?,
    override val nameEn: String?,
    override val year: Int,
    override val genres: List<MovieGenre>?,
): MovieListItemDto(nameRu, nameEn, year, genres)