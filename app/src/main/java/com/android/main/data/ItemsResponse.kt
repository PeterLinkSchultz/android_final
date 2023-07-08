package com.android.main.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemsResponse<T>(
    val total: Int,
    val totalPages: Int?,
    val items: List<T>
)
