package com.android.main.data.movie

interface MovieFilterParams {
    val countries: Int?
    val genres: Int?
    val type: MovieType?
    val page: Int?
    val ratingFrom: Int?
    val ratingTo: Int?
    val yearFrom: Int?
    val yearTo: Int?
}