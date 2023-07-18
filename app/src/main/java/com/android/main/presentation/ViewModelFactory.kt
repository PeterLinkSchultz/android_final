package com.android.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

open class ViewModelFactory(private val viewModel: ViewModel): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModel as T
    }
}