package com.android.main.presentation.gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.android.main.data.image.ImageType
import com.android.main.databinding.GalleryDetailFragmentBinding
import com.android.main.di.DaggerAppComponent
import com.android.main.presentation.LoadDataState
import com.android.main.presentation.decorator.GridItemDecorator
import com.android.main.presentation.movie.MovieDetailFragment.Companion.KEY_MOVIE_ID
import com.android.main.presentation.textNumRadioGroup.TextNumRadioButton
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class GalleryDetailFragment: Fragment() {

    private var _binding: GalleryDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var listAdapter: GalleryDetailListAdapter
    class ViewModelFactory @Inject constructor(viewModel: GalleryDetailViewModel):
        com.android.main.presentation.ViewModelFactory(viewModel)

    private val viewModel: GalleryDetailViewModel by viewModels {
        DaggerAppComponent.create().galleryViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = GalleryDetailFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.buttonsData.observe(viewLifecycleOwner) {
            when (it) {
                is LoadDataState.Loading -> binding.loader.visibility = View.VISIBLE
                is LoadDataState.Failure -> {

                }
                is LoadDataState.Success -> {
                    binding.loader.visibility = View.GONE
                    createButtons(it.model)
                }
            }
        }

        with (binding.recyclerList) {
            val gridLayoutManager = GridLayoutManager(this@GalleryDetailFragment.context, 4, GridLayoutManager.VERTICAL, false)

            gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if ((position + 1) % 3 == 0) 4 else 2
                }
            }

            addItemDecoration(GridItemDecorator(context, 16))
            setHasFixedSize(true)
            listAdapter = GalleryDetailListAdapter()
            adapter = listAdapter
            layoutManager = gridLayoutManager
        }
        arguments?.getInt(KEY_MOVIE_ID)?.let {
            viewModel.loadButtonsData(it)
            viewModel.initPagedMovies(it)
        }
        viewModel.pagedMovies.onEach {
            listAdapter.submitData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun createButtons(data: List<GalleryDetailViewModel.ButtonData>) {
        binding.buttonsType.let { radioGroup->
            data.forEach {
                val radio = TextNumRadioButton(requireContext())

                radioGroup.addView(radio.apply {
                    id = it.type.ordinal
                    setContent(it.type.toString(), it.count)
                })

                if (radioGroup.checked == View.NO_ID) {
                    radioGroup.setChecked(it.type.ordinal)
                }
            }
            radioGroup.setOnChangeListener { id ->
                viewModel.type = ImageType.values()[id]
                listAdapter.refresh()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}