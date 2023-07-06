package com.android.main.data.movie

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieListResponse(
    val total: Int,
    val items: List<MovieDto>
)