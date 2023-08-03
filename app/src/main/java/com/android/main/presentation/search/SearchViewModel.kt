package com.android.main.presentation.search

import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.cachedIn
import com.android.main.R
import com.android.main.data.movie.MovieFilterParams
import com.android.main.data.movie.MovieShortDto
import com.android.main.data.movie.MovieType
import com.example.main.data.KinopoiskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchViewModel @Inject constructor(val kinopoiskRepository: KinopoiskRepository): ViewModel() {

    var pagedMovies: Flow<PagingData<MovieShortDto>>
    private var loadingLiveData = MutableLiveData(false)
    val loading get() = loadingLiveData

    private val countriesID = listOf(34, 5, 9, 1, 3)
    private val genresID = listOf(13, 4, 11, 10, 2)

    private val filterValues: MutableMap<String, Int> = mutableMapOf(
        FILTER_KEY_TYPE to MovieType.ALL.ordinal,
        FILTER_KEY_COUNTRIES to 9,
        FILTER_KEY_YEAR_FROM to 2000,
    )

    val getFilterValues get() = filterValues

    val countries = mutableMapOf<Int, String>()
    val genres = mutableMapOf<Int, String>()

    init {
        pagedMovies = Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 3),
            pagingSourceFactory = { object: PagingSource<Int, MovieShortDto>() {
                private val FIRST_PAGE = 1
                override fun getRefreshKey(state: PagingState<Int, MovieShortDto>): Int? = FIRST_PAGE

                override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieShortDto> {
                    val page = params.key ?: FIRST_PAGE
                    loadingLiveData.value = true

                    return kotlin.runCatching {
                        kinopoiskRepository.getMovies(object : MovieFilterParams {
                            override var countries = filterValues[FILTER_KEY_COUNTRIES]
                            override var genres = filterValues[FILTER_KEY_GENRES]
                            override var type = MovieType.values()[filterValues[FILTER_KEY_TYPE] ?: MovieType.ALL.ordinal]
                            override var page = page
                            override var ratingFrom = filterValues[FILTER_KEY_RATING_FROM]
                            override val ratingTo = filterValues[FILTER_KEY_RATING_TO]
                            override val yearFrom = filterValues[FILTER_KEY_YEAR_FROM]
                            override val yearTo = filterValues[FILTER_KEY_YEAR_TO]
                        }).items
                    }.fold(
                        onSuccess = {
                            loadingLiveData.value = false
                            LoadResult.Page(
                                data = it,
                                prevKey = null,
                                nextKey = if (it.isEmpty()) null else page + 1
                            )
                        },
                        onFailure = {
                            loadingLiveData.value = false
                            LoadResult.Error(it)
                        },
                    )
                }
            }
            }
        ).flow.cachedIn(viewModelScope)
    }

    fun getPaged(): Flow<PagingData<MovieShortDto>> {
        return Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 3),
            pagingSourceFactory = { object: PagingSource<Int, MovieShortDto>() {
                private val FIRST_PAGE = 1
                override fun getRefreshKey(state: PagingState<Int, MovieShortDto>): Int? = FIRST_PAGE

                override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieShortDto> {
                    val page = params.key ?: FIRST_PAGE
                    loadingLiveData.value = true

                    return kotlin.runCatching {
                        kinopoiskRepository.getMovies(object : MovieFilterParams {
                            override var countries = filterValues[FILTER_KEY_COUNTRIES]
                            override var genres = filterValues[FILTER_KEY_GENRES]
                            override var type = MovieType.values()[filterValues[FILTER_KEY_TYPE] ?: MovieType.ALL.ordinal]
                            override var page = page
                            override var ratingFrom = filterValues[FILTER_KEY_RATING_FROM]
                            override val ratingTo = filterValues[FILTER_KEY_RATING_TO]
                            override val yearFrom = filterValues[FILTER_KEY_YEAR_FROM]
                            override val yearTo = filterValues[FILTER_KEY_YEAR_TO]
                        }).items
                    }.fold(
                        onSuccess = {
                            loadingLiveData.value = false
                            LoadResult.Page(
                                data = it,
                                prevKey = null,
                                nextKey = if (it.isEmpty()) null else page + 1
                            )
                        },
                        onFailure = {
                            Log.e("Search", it.stackTraceToString())
                            LoadResult.Error(it)
                        },
                    )
                }
            }
            }
        ).flow.cachedIn(viewModelScope)
    }

    fun initFiltersValues(resources: Resources) {
        val countriesName = resources.getStringArray(R.array.filter_country)
        val genresName = resources.getStringArray(R.array.filter_genre)

        countriesID.forEachIndexed { index, id ->
            countries[id] = countriesName[index]
        }
        genresID.forEachIndexed { index, id ->
            genres[id] = genresName[index]
        }
    }

    fun updateValue(key: String, value: Int) {
        Log.i("Filter key", "$key $value")
        filterValues[key] = value
    }

    companion object {
        val FILTER_KEY_TYPE = "type"
        val FILTER_KEY_GENRES = "genres"
        val FILTER_KEY_RATING_FROM = "ratingFrom"
        val FILTER_KEY_RATING_TO = "ratingTo"
        val FILTER_KEY_YEAR_FROM = "yearFrom"
        val FILTER_KEY_YEAR_TO = "yearTo"
        val FILTER_KEY_COUNTRIES = "countries"
    }
}