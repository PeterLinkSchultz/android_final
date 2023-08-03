package com.android.main.data.movie.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.main.data.movie.MovieShortDto

abstract class MoviePagingSource: PagingSource<Int, MovieShortDto>() {
    final override fun getRefreshKey(state: PagingState<Int, MovieShortDto>): Int = FIRST_PAGE

    final override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieShortDto> {
        val page = params.key ?: FIRST_PAGE

        return kotlin.runCatching {
            getList(page)
        }.fold(
            onSuccess = {
                LoadResult.Page(
                    data = it,
                    prevKey = null,
                    nextKey = if (it.isEmpty()) null else page + 1
                )
            },
            onFailure = {
                LoadResult.Error(it)
            }
        )
    }

    abstract suspend fun getList(page: Int): List<MovieShortDto>

    private companion object {
        val FIRST_PAGE = 1
    }
}