package com.android.main.presentation.movieCategoryList.serires

import androidx.fragment.app.viewModels
import com.android.main.R
import com.android.main.di.DaggerAppComponent
import com.android.main.presentation.movieCategoryList.CategoryListFragment
import com.android.main.presentation.movieCategoryList.CategoryViewModelFactory
import com.android.main.presentation.movieCategoryList.MovieCategoryListViewModel
import javax.inject.Inject

class SeriesCategoryListFragment: CategoryListFragment() {
    class ViewModelFactory @Inject constructor(categoryViewModel: SeriesCategoryListViewModel): CategoryViewModelFactory(categoryViewModel)

    private val viewModel: SeriesCategoryListViewModel by viewModels {
        DaggerAppComponent.create().seriesCategoryViewModelFactory()
    }

    override fun getViewModel(): MovieCategoryListViewModel {
        return viewModel
    }

    override var navDetailId = R.id.action_seriesCategoryListFragment_to_movieDetailFragment
}