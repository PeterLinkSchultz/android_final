package com.android.main.data.movie

import com.android.main.entity.Movie
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDto(
    override val kinopoiskId: Int,
    override val nameRu: String,
    override val posterUrl: String,
    override val posterUrlPreview: String,
    override val ratingImdb: Double?,
    override val shortDescription: String?,
    override val description: String?
) : Movie