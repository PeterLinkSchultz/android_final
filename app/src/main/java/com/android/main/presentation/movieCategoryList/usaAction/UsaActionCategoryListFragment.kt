package com.android.main.presentation.movieCategoryList.usaAction

import androidx.fragment.app.viewModels
import com.android.main.R
import com.android.main.di.DaggerAppComponent
import com.android.main.presentation.movieCategoryList.CategoryListFragment
import com.android.main.presentation.movieCategoryList.CategoryViewModelFactory
import com.android.main.presentation.movieCategoryList.MovieCategoryListViewModel
import javax.inject.Inject

class UsaActionCategoryListFragment: CategoryListFragment() {
    class ViewModelFactory @Inject constructor(categoryViewModel: UsaActionCategoryListViewModel): CategoryViewModelFactory(categoryViewModel)

    private val viewModel: UsaActionCategoryListViewModel by viewModels {
        DaggerAppComponent.create().usaActionCategoryViewModelFactory()
    }

    override fun getViewModel(): MovieCategoryListViewModel {
        return viewModel
    }

    override var navDetailId = R.id.action_usaActionCategoryListFragment_to_movieDetailFragment
}