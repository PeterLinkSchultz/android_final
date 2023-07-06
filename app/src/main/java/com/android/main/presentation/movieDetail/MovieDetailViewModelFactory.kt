package com.android.main.presentation.movieDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class MovieDetailViewModelFactory @Inject constructor(private val mainViewModel: MovieDetailViewModel): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return mainViewModel as T
    }
}