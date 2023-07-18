package com.android.main.presentation.movieCategoryList.popular

import androidx.fragment.app.viewModels
import com.android.main.R
import com.android.main.di.DaggerAppComponent
import com.android.main.presentation.movieCategoryList.CategoryListFragment
import com.android.main.presentation.movieCategoryList.CategoryViewModelFactory
import com.android.main.presentation.movieCategoryList.MovieCategoryListViewModel
import javax.inject.Inject

class PopularCategoryListFragment() : CategoryListFragment() {
    class ViewModelFactory @Inject constructor(categoryViewModel: PopularCategoryListViewModel): CategoryViewModelFactory(categoryViewModel)

    private val viewModel: PopularCategoryListViewModel by viewModels {
        DaggerAppComponent.create().popularCategoryViewModelFactory()
    }

    override fun getViewModel(): MovieCategoryListViewModel {
        return viewModel
    }

    override var navDetailId = R.id.action_popularCategoryListFragment_to_movieDetailFragment
}