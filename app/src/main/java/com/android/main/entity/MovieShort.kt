package com.android.main.entity

import com.android.main.data.movie.MovieGenre

interface MovieShort {
    val filmId: Int?
    val kinopoiskId: Int?
    val nameRu: String?
    val nameEn: String?
    val nameOriginal: String?
    val posterUrl: String
    val posterUrlPreview: String
    val ratingImdb: Double?
    val genres: List<MovieGenre>?
    val rating: String?
    val year: Int?
}