package com.android.main.data.movie

import com.android.main.entity.MovieSeriesEpisode
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieSeriesEpisodeDto(
    override val seasonNumber: Int,
    override val episodeNumber: Int,
    override val nameRu: String?,
    override val nameEn: String?,
    override val releaseDate: String?
) : MovieSeriesEpisode