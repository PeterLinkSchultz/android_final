package com.android.main.data.movie

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieByKeywordDto(
    val filmId: Int,
    val rating: Double?,
    override val nameRu: String?,
    override val nameEn: String?,
    override val year: Int,
    override val genres: List<MovieGenre>?,
): MovieListItemDto(nameRu, nameEn, year, genres)