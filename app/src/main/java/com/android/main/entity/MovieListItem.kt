package com.android.main.entity

import com.android.main.data.movie.MovieGenre

interface MovieListItem {
    val nameRu: String?
    val nameEn: String?
    val year: Int
    val genres: List<MovieGenre>?
}