package com.android.main.entity

interface Movie {
    val kinopoiskId: Int
    val nameRu: String
    val posterUrl: String
    val posterUrlPreview: String
    val ratingImdb: Double?
    val shortDescription: String?
    val description: String?
}