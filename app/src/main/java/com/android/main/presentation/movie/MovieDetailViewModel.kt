package com.android.main.presentation.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.main.data.ItemsResponse
import com.android.main.data.image.GalleryImageDto
import com.android.main.data.movie.MovieDto
import com.android.main.data.movie.MovieSeriesSeasonDto
import com.android.main.data.movie.MovieShortDto
import com.android.main.data.staff.StaffShortDto
import com.android.main.domain.GetAllStaffCase
import com.android.main.domain.GetMovieCase
import com.android.main.domain.GetMovieImagesCase
import com.android.main.domain.GetSeasonsTotal
import com.android.main.domain.GetSimilarMoviesCase
import com.android.main.presentation.LoadDataState
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val getMovieCase: GetMovieCase,
    private val getAllStaffCase: GetAllStaffCase,
    private val getMovieImagesCase: GetMovieImagesCase,
    private val getSimilarMoviesCase: GetSimilarMoviesCase,
    private val getSeasonsTotal: GetSeasonsTotal,
    ): ViewModel() {
    private val movieLiveData = MutableLiveData<LoadDataState<MovieInfo>>()

    val movieData: LiveData<LoadDataState<MovieInfo>>
        get() = movieLiveData

    fun loadData(movieId: Int) {
        viewModelScope.launch {
            movieLiveData.value = LoadDataState.Loading()

            val movieData = async { getMovieCase.execute(movieId) }
            val staffData = async { getAllStaffCase.execute(movieId) }
            val galleryData = async { getMovieImagesCase.execute(movieId, 1) }
            val similarData = async { getSimilarMoviesCase.execute(movieId) }
            val seasonsData = async { getSeasonsTotal.execute(movieId) }

            awaitAll(movieData, staffData, galleryData, similarData)
                .runCatching {
                    val detail = get(0) as MovieDto
                    val seasonsTotal = if (detail.serial) seasonsData.await() else null

                    MovieInfo(get(0) as MovieDto, get(1) as List<StaffShortDto>, get(2) as ItemsResponse<GalleryImageDto>, get(3) as ItemsResponse<MovieShortDto>, seasonsTotal)
                }
                .onSuccess {
                    Log.i("Movie detail", "success")
                    movieLiveData.value = LoadDataState.Success(it)
                }
                .onFailure {
                    movieLiveData.value = LoadDataState.Failure()
                }
        }
    }

    data class MovieInfo(
        val movieDetail: MovieDto,
        val staffList: List<StaffShortDto>,
        val gallery: ItemsResponse<GalleryImageDto>,
        val similarList: ItemsResponse<MovieShortDto>,
        val seasonsTotal: ItemsResponse<MovieSeriesSeasonDto>?,
    )
}