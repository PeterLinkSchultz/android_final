package com.android.main.data.staff

import android.os.Parcelable
import com.android.main.entity.StaffMovie
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class StaffMovieDto(
    override val filmId: Int,
    override val nameRu: String?,
    override val nameEn: String?,
    override val rating: Double?,
    override val professionKey: Profession
): StaffMovie, Parcelable