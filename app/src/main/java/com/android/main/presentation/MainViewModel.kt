package com.android.main.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.main.data.movie.MovieShortDto
import com.android.main.data.movie.TopMoviesType
import com.android.main.domain.GetCountryGenreMoviesCase
import com.android.main.domain.GetPremieresCase
import com.android.main.domain.GetSeriesMoviesCase
import com.android.main.domain.GetTopMoviesCase
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getPremieresCase: GetPremieresCase,
    private val getTopMoviesCase: GetTopMoviesCase,
    private val getCountryGenreMoviesCase: GetCountryGenreMoviesCase,
    private val getSeriesMoviesCase: GetSeriesMoviesCase
    ) : ViewModel() {

    private val allListsLiveData = MutableLiveData<LoadDataState<AllLists>>()

    val allLists: LiveData<LoadDataState<AllLists>>
    get() = allListsLiveData

    fun loadAllData() {
        viewModelScope.launch {
            allListsLiveData.value = LoadDataState.Loading()

            val premieres = async { getPremieresCase.execute().items }
            val popular = async { getTopMoviesCase.execute(TopMoviesType.TOP_100_POPULAR_FILMS).films }
            val top = async { getTopMoviesCase.execute(TopMoviesType.TOP_250_BEST_FILMS).films }
            val usaActions = async { getCountryGenreMoviesCase.execute(1, 11, 1).items }
            val frenchDrams = async { getCountryGenreMoviesCase.execute(3, 2, 1).items }
            val series = async { getSeriesMoviesCase.execute(1).items }

            try {
                awaitAll(premieres, popular, top, usaActions, frenchDrams, series)
                    .runCatching {
                        AllLists(
                            get(0).subList(0, 7),
                            get(1).subList(0, 7),
                            get(2).subList(0, 7),
                            get(3).subList(0, 7),
                            get(4).subList(0, 7),
                            get(5).subList(0, 7)
                        )
                    }
                    .onSuccess {
                        allListsLiveData.value = LoadDataState.Success(it)
                    }
                    .onFailure {
                        Log.e("Movie main", it.stackTraceToString())
                        allListsLiveData.value = LoadDataState.Failure()
                    }
            } catch (e: Exception) {
                Log.e("Movie main", e.stackTraceToString())
            }
        }
    }

    data class AllLists(
        val premieres: List<MovieShortDto>,
        val top: List<MovieShortDto>,
        val popular: List<MovieShortDto>,
        val usaActions: List<MovieShortDto>,
        val frenchDrams: List<MovieShortDto>,
        val series: List<MovieShortDto>,
    )
}