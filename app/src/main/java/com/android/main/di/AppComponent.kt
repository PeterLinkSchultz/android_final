package com.android.main.di

import com.android.main.presentation.MainViewModelFactory
import com.android.main.presentation.movieDetail.MovieDetailViewModelFactory
import dagger.Component

@Component
interface AppComponent {
    fun mainViewModelFactory(): MainViewModelFactory
    fun movieDetailViewModelFactory(): MovieDetailViewModelFactory
}