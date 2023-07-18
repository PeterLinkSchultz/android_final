package com.android.main.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.android.main.databinding.FragmentHomeBinding
import com.android.main.di.DaggerAppComponent
import com.android.main.presentation.LoadDataState
import com.android.main.presentation.MainViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val mainViewModel: MainViewModel by viewModels {
        DaggerAppComponent.create().mainViewModelFactory()
    }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        mainViewModel.loadAllData()

        mainViewModel.allLists.observe(viewLifecycleOwner) {
            when (it) {
                is LoadDataState.Loading -> {}
                is LoadDataState.Failure -> {}
                is LoadDataState.Success -> {
                    binding.premieresList.setData(it.model.premieres)
                    binding.popularList.setData(it.model.popular)
                    binding.topList.setData(it.model.top)
                    binding.usaActionList.setData(it.model.usaActions)
                    binding.frenchDramaList.setData(it.model.frenchDrams)
                    binding.seriesList.setData(it.model.series)
                }
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}