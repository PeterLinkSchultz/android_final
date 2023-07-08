package com.android.main.presentation.movieDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.main.data.ItemsResponse
import com.android.main.data.image.GalleryImageDto
import com.android.main.data.movie.MovieDto
import com.android.main.data.movie.MovieShortDto
import com.android.main.data.staff.StaffDto
import com.android.main.domain.GetAllStaffCase
import com.android.main.domain.GetMovieCase
import com.android.main.domain.GetMovieImagesCase
import com.android.main.domain.GetSimilarMoviesCase
import com.android.main.presentation.LoadDataState
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(private val getMovieCase: GetMovieCase, private val getAllStaffCase: GetAllStaffCase, private val getMovieImagesCase: GetMovieImagesCase, private val getSimilarMoviesCase: GetSimilarMoviesCase): ViewModel() {
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

            awaitAll(movieData, staffData, galleryData, similarData)
                .runCatching {
                    MovieInfo(get(0) as MovieDto, get(1) as List<StaffDto>, get(2) as ItemsResponse<GalleryImageDto>, get(3) as ItemsResponse<MovieShortDto>)
                }
                .onSuccess {
                    movieLiveData.value = LoadDataState.Success(it)
                }
                .onFailure {
                    movieLiveData.value = LoadDataState.Failure()
                }
        }
    }

    data class MovieInfo(
        val movieDetail: MovieDto,
        val staffList: List<StaffDto>,
        val gallery: ItemsResponse<GalleryImageDto>,
        val similarList: ItemsResponse<MovieShortDto>
    )
}