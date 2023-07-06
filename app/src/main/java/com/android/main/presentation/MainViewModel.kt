package com.android.main.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.main.domain.GetPremieresCase
import com.android.main.entity.Movie
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class MainViewModel @Inject constructor(private val getPremieresCase: GetPremieresCase) : ViewModel() {

    private val premieresLiveData = MutableLiveData<List<Movie>>()
    private val loadingLiveData = MutableLiveData<Boolean>()

    val premieres: LiveData<List<Movie>>
    get() = premieresLiveData
    val loading: LiveData<Boolean>
    get() = loadingLiveData

    fun loadPremieres() {
        viewModelScope.launch {
            loadingLiveData.postValue(true)
            try {
                premieresLiveData.postValue(getPremieresCase.execute().items.subList(0, 6))
            } catch (e: Exception) {
                Log.e("Load premieres", e.toString())
            }
            loadingLiveData.postValue(false)
        }
    }
}