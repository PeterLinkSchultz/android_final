package com.android.main.presentation

sealed class LoadDataState<T> {
    data class Success<T>(val model: T) : LoadDataState<T>()
    data class Failure<T>(
        val exception: Exception? = null,
        val modelTypeId: String? = null
    ) : LoadDataState<T>()
    class Loading<T>() : LoadDataState<T>()
}