package com.android.main.entity

import com.android.main.data.staff.Profession

interface StaffMovie {
    val filmId: Int
    val nameRu: String?
    val nameEn: String?
    val rating: Double?
    val professionKey: Profession
}