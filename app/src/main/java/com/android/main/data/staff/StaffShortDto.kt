package com.android.main.data.staff

import com.android.main.entity.StaffShort
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StaffShortDto(
    override val staffId: Int,
    override val nameRu: String?,
    override val nameEn: String,
    override val description: String?,
    override val posterUrl: String,
    override val professionText: String,
    override val professionKey: Profession,
): StaffShort