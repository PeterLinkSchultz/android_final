package com.android.main.data.movie

import com.android.main.entity.MovieSeriesSeason
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieSeriesSeasonDto(
    override val number: Int,
    override val episodes: List<MovieSeriesEpisodeDto>
) : MovieSeriesSeason