package com.android.main.presentation.person

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.android.main.R
import com.android.main.data.staff.Sex
import com.android.main.data.staff.StaffDto
import com.android.main.databinding.PersonDetailFragmentBinding
import com.android.main.di.DaggerAppComponent
import com.android.main.presentation.LoadDataState
import com.android.main.presentation.person.PersonFilmographyFragment.Companion.KEY_FILM_LIST
import com.android.main.presentation.person.PersonFilmographyFragment.Companion.KEY_PERSON_GENDER
import com.android.main.presentation.person.PersonFilmographyFragment.Companion.KEY_PERSON_NAME
import com.bumptech.glide.Glide
import javax.inject.Inject

class PersonDetailFragment: Fragment() {

    private var _binding: PersonDetailFragmentBinding? = null
    private val binding
        get() = _binding!!

    class ViewModelFactory @Inject constructor(viewMode: PersonDetailViewModel): com.android.main.presentation.ViewModelFactory(viewMode)

    private val viewModel: PersonDetailViewModel by viewModels {
        DaggerAppComponent.create().personViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PersonDetailFragmentBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.data.observe(viewLifecycleOwner) {
            when (it) {
                is LoadDataState.Loading -> {}
                is LoadDataState.Failure -> {}
                is LoadDataState.Success -> {
                    bindData(it.model)
                }
            }
        }

        arguments?.getInt(KEY_STAFF_ID)?.let {
            viewModel.loadData(it)
        }
    }

    private fun bindData(data: StaffDto) {
        val personName = data.nameRu ?: data.nameEn

        binding.staffDetailName.text = personName
        binding.staffDetailRole.text = data.profession
        binding.staffDetailFilmographyCount.text = resources.getQuantityString(
            R.plurals.filmography_count,
            data.films.size,
            data.films.size
        )
        Glide.with(binding.root)
            .load(data.posterUrl)
            .into(binding.staffDetailPoster)
        binding.staffDetailFilmographyButtonMore.setOnClickListener {
            findNavController().navigate(
                R.id.action_personDetailFragment_to_personFilmographyFragment,
                bundleOf(
                    KEY_FILM_LIST to data.films,
                    KEY_PERSON_NAME to personName,
                    KEY_PERSON_GENDER to (data.sex == Sex.MALE)
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    companion object {
        val KEY_STAFF_ID = "staffId"
    }
}