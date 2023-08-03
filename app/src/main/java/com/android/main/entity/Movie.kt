package com.android.main.entity

interface Movie {
    val kinopoiskId: Int
    val nameRu: String?
    val nameEn: String?
    val posterUrl: String
    val posterUrlPreview: String
    val ratingImdb: Double?
    val shortDescription: String?
    val description: String?
    val serial: Boolean
}