package com.android.main.presentation.movieDetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.main.data.movie.MovieDto
import com.android.main.data.staff.StaffDto
import com.android.main.domain.GetAllStaffCase
import com.android.main.domain.GetMovieCase
import com.android.main.presentation.LoadDataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(private val getMovieCase: GetMovieCase, private val getAllStaffCase: GetAllStaffCase): ViewModel() {
    private val movieLiveData = MutableLiveData<LoadDataState<MovieInfo>>()

    val movieData: LiveData<LoadDataState<MovieInfo>>
        get() = movieLiveData

    fun loadData(movieId: Int) {
        viewModelScope.launch {
            movieLiveData.value = LoadDataState.Loading()

            val movieData = async { getMovieCase.execute(movieId) }
            val staffData = async { getAllStaffCase.execute(movieId) }

            awaitAll(movieData, staffData)
                .runCatching {
                    MovieInfo(get(0) as MovieDto, get(1) as List<StaffDto>)
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
    )
}