package com.android.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.paging.PagingData
import com.android.main.data.movie.MovieShortDto
import com.android.main.databinding.ActivityMainBinding
import com.android.main.di.DaggerAppComponent
import com.android.main.presentation.search.SearchController
import com.android.main.presentation.search.SearchViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ActivityMain : AppCompatActivity(), SearchController {

    private lateinit var binding: ActivityMainBinding

    class ViewModelFactory @Inject constructor(viewModel: SearchViewModel):
        com.android.main.presentation.ViewModelFactory(viewModel)
    override val searchViewModel: SearchViewModel by viewModels {
        DaggerAppComponent.create().searchViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_search, R.id.navigation_notifications
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView[0].setOnClickListener { view ->
            Log.i("Nav", view.toString())
        }
        supportActionBar!!.hide()
        searchViewModel.initFiltersValues(resources)
    }

    override fun updateSearchList() {
        TODO("Not yet implemented")
    }
}