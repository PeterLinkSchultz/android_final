package com.android.main.entity

import com.android.main.data.staff.Sex

interface Staff {
    val personId: Int
    val nameRu: String?
    val nameEn: String
    val profession: String?
    val posterUrl: String
    val films: List<StaffMovie>
    val sex: Sex
}