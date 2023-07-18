package com.android.main.di

import com.android.main.presentation.MainViewModelFactory
import com.android.main.presentation.movieCategoryList.top.TopCategoryListFragment
import com.android.main.presentation.movieCategoryList.frenchDrama.FrenchDramaCategoryListFragment
import com.android.main.presentation.movieCategoryList.popular.PopularCategoryListFragment
import com.android.main.presentation.movieCategoryList.serires.SeriesCategoryListFragment
import com.android.main.presentation.movieCategoryList.usaAction.UsaActionCategoryListFragment
import com.android.main.presentation.movie.MovieDetailViewModelFactory
import com.android.main.presentation.movie.SeriesDetailFragment
import com.android.main.presentation.person.PersonDetailFragment
import dagger.Component

@Component
interface AppComponent {
    fun mainViewModelFactory(): MainViewModelFactory
    fun movieDetailViewModelFactory(): MovieDetailViewModelFactory
    fun movieTopCategoryViewModelFactory(): TopCategoryListFragment.ViewModelFactory
    fun popularCategoryViewModelFactory(): PopularCategoryListFragment.ViewModelFactory
    fun seriesCategoryViewModelFactory(): SeriesCategoryListFragment.ViewModelFactory
    fun usaActionCategoryViewModelFactory(): UsaActionCategoryListFragment.ViewModelFactory
    fun frenchDramaCategoryViewModelFactory(): FrenchDramaCategoryListFragment.ViewModelFactory
    fun seriesViewModelFactory(): SeriesDetailFragment.VieModelFactory
    fun personViewModelFactory(): PersonDetailFragment.ViewModelFactory
}