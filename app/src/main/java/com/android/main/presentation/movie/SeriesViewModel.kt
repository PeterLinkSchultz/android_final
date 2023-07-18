package com.android.main.presentation.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.main.data.ItemsResponse
import com.android.main.data.movie.MovieSeriesSeasonDto
import com.android.main.domain.GetSeasonsTotal
import com.android.main.presentation.LoadDataState
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class SeriesViewModel @Inject constructor(private val getSeasonsTotal: GetSeasonsTotal): ViewModel() {

    private val liveData = MutableLiveData<LoadDataState<ItemsResponse<MovieSeriesSeasonDto>>>()
    val data: LiveData<LoadDataState<ItemsResponse<MovieSeriesSeasonDto>>>
        get() = liveData

    fun loadData(movieId: Int) {
        viewModelScope.launch {
            val movieData = async { getSeasonsTotal.execute(movieId) }

            liveData.value = LoadDataState.Loading()

            kotlin.runCatching {
                movieData.await()
            }.onSuccess {
                liveData.value = LoadDataState.Success(it)
            }.onFailure {
                liveData.value = LoadDataState.Failure()
            }
        }
    }

}