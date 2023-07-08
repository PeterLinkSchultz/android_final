package com.android.main.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.main.data.movie.MovieShortDto
import com.android.main.domain.GetPremieresCase
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class MainViewModel @Inject constructor(private val getPremieresCase: GetPremieresCase) : ViewModel() {

    private val premieresLiveData = MutableLiveData<List<MovieShortDto>>()
    private val loadingLiveData = MutableLiveData<Boolean>()

    val premieres: LiveData<List<MovieShortDto>>
    get() = premieresLiveData
    val loading: LiveData<Boolean>
    get() = loadingLiveData

    fun loadPremieres() {
        viewModelScope.launch {
            loadingLiveData.postValue(true)
            try {
                val response = getPremieresCase.execute()
                Log.i("Premieres", response.items[0].toString())
                premieresLiveData.postValue(getPremieresCase.execute().items.subList(0, 6))
            } catch (e: Exception) {
                Log.e("Load premieres", e.toString())
            }
            loadingLiveData.postValue(false)
        }
    }
}