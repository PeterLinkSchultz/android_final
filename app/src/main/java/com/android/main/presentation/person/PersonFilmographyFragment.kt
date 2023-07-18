package com.android.main.presentation.person

import android.app.Instrumentation
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.main.data.staff.StaffMovieDto
import com.android.main.databinding.PersonFilmographyFragmentBinding
import com.android.main.databinding.RoundedRadioButtonBinding
import com.android.main.extensions.FromDpToPx

class PersonFilmographyFragment: Fragment() {

    private var _binding: PersonFilmographyFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel = PersonalFilmographyModelView()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PersonFilmographyFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.getParcelableArrayList<StaffMovieDto>(KEY_FILM_LIST)?.let {
            viewModel.setData(it)
        }
        createButtons()
    }

    private fun createButtons() {
        viewModel.labels.forEach { (profession, i) ->
            binding.buttonsRole.addView(RoundedRadioButtonBinding.inflate(LayoutInflater.from(context)).radioButton.apply {
                this.text = "$profession $i"
                this.id = i
                layoutParams = ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    this.marginEnd = (8).FromDpToPx(context)
                }
            })
            binding.buttonsRole.check(1)
            binding.buttonsRole.setOnCheckedChangeListener { radioGroup, i ->
                // bindData(i, (viewModel.data.value as LoadDataState.Success).model.items[i - 1].episodes)
            }
        }
    }

    companion object {
        val KEY_FILM_LIST = "film_list"
        val KEY_PERSON_NAME = "person_name"
    }
}