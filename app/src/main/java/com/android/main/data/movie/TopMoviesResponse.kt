package com.android.main.data.movie

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TopMoviesResponse(
    val films: List<MovieShortDto>,
    val pagesCount: Int
)