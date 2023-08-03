package com.android.main.data.staff

import com.android.main.entity.Staff
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StaffDto(
    override val personId: Int,
    override val nameRu: String?,
    override val nameEn: String,
    override val profession: String?,
    override val posterUrl: String,
    override val films: List<StaffMovieDto>,
    override val sex: Sex
): Staff