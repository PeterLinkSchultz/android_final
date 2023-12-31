package com.android.main.presentation.movieCategoryList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

open class CategoryViewModelFactory(private val categoryViewModel: MovieCategoryListViewModel): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
    ): T {
        return categoryViewModel as T
    }
}