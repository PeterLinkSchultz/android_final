package com.android.main.entity

import com.android.main.data.staff.Profession

interface Staff {
    val staffId: Int
    val nameRu: String?
    val nameEn: String
    val description: String?
    val posterUrl: String
    val professionText: String
    val professionKey: Profession
}