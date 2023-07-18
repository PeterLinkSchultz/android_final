package com.android.main.presentation.movieCategoryList.frenchDrama

import androidx.fragment.app.viewModels
import com.android.main.R
import com.android.main.di.DaggerAppComponent
import com.android.main.presentation.movieCategoryList.CategoryListFragment
import com.android.main.presentation.movieCategoryList.CategoryViewModelFactory
import com.android.main.presentation.movieCategoryList.MovieCategoryListViewModel
import javax.inject.Inject

class FrenchDramaCategoryListFragment: CategoryListFragment() {
    class ViewModelFactory @Inject constructor(categoryViewModel: FrenchDramaCategoryListViewModel): CategoryViewModelFactory(categoryViewModel)

    private val viewModel: FrenchDramaCategoryListViewModel by viewModels {
        DaggerAppComponent.create().frenchDramaCategoryViewModelFactory()
    }

    override fun getViewModel(): MovieCategoryListViewModel {
        return viewModel
    }

    override var navDetailId = R.id.action_frenchDramaCategoryListFragment_to_movieDetailFragment
}