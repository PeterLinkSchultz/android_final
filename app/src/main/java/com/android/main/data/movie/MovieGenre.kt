package com.android.main.data.movie

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieGenre(
    val genre: String
)
