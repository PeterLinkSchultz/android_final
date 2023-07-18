package com.android.main.entity

interface Staff {
    val personId: Int
    val nameRu: String?
    val nameEn: String
    val profession: String?
    val posterUrl: String
    val films: List<StaffMovie>
}