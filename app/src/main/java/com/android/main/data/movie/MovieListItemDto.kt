package com.android.main.data.movie

import com.android.main.data.movie.MovieGenre
import com.android.main.entity.MovieListItem

open class MovieListItemDto(
    override val nameRu: String?,
    override val nameEn: String?,
    override val year: Int,
    override val genres: List<MovieGenre>?
): MovieListItem