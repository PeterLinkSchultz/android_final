package com.android.main.presentation.gallery

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
import com.android.main.data.image.GalleryImageDto
import com.android.main.data.image.ImageType
import com.android.main.domain.GetMovieImagesCase
import com.android.main.presentation.LoadDataState
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class GalleryDetailViewModel @Inject constructor(private val getMovieImagesCase: GetMovieImagesCase): ViewModel() {

    private val buttonsLiveData = MutableLiveData<LoadDataState<List<ButtonData>>>()
    val buttonsData get() = buttonsLiveData
    var type: ImageType = ImageType.STILL

    lateinit var pagedMovies: Flow<PagingData<GalleryImageDto>>

    fun initPagedMovies(movieId: Int) {
        pagedMovies = Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 3),
            pagingSourceFactory = { object: PagingSource<Int, GalleryImageDto>() {
                private val FIRST_PAGE = 1
                override fun getRefreshKey(state: PagingState<Int, GalleryImageDto>): Int? = FIRST_PAGE

                override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GalleryImageDto> {
                    val page = params.key ?: FIRST_PAGE

                    return kotlin.runCatching {
                        getMovieImagesCase.execute(movieId, page, type).items
                    }.fold(
                        onSuccess = {
                            Log.i("Load", "${params.key} ${params.loadSize} ${it.size}")
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
            }
            }
        ).flow.cachedIn(viewModelScope)
    }

    fun loadButtonsData(movieId: Int) {
        viewModelScope.launch {
            buttonsLiveData.value = LoadDataState.Loading()

            val getStillImages = async { getMovieImagesCase.execute(movieId, 1, ImageType.STILL) }
            val getShootingImages = async { getMovieImagesCase.execute(movieId, 1, ImageType.SHOOTING) }
            val getPosterImages = async { getMovieImagesCase.execute(movieId, 1, ImageType.POSTER) }

            awaitAll(getStillImages, getShootingImages, getPosterImages)
                .runCatching {
                    listOf(
                        ButtonData(ImageType.STILL, get(0).total),
                        ButtonData(ImageType.SHOOTING, get(1).total),
                        ButtonData(ImageType.POSTER, get(2).total),
                    )
                }.onSuccess {
                    buttonsLiveData.value = LoadDataState.Success(it)
                }.onFailure {
                    buttonsLiveData.value = LoadDataState.Failure()
                }
        }
    }

    data class ButtonData(
        val type: ImageType,
        val count: Int,
    )
}