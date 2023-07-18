package com.android.main.data.movie

import com.android.main.entity.MovieShort
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieShortDto(
    override val kinopoiskId: Int?,
    override val filmId: Int?,
    override val nameRu: String?,
    override val nameEn: String?,
    override val posterUrl: String,
    override val posterUrlPreview: String,
    override val ratingImdb: Double?,
    override val genres: List<MovieGenre>?,
): MovieShort
