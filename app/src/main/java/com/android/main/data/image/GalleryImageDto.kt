package com.android.main.data.image

import com.android.main.entity.GalleryImage
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GalleryImageDto(override val imageUrl: String, override val previewUrl: String): GalleryImage {}