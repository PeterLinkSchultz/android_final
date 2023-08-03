package com.android.main.presentation.person

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.main.R
import com.android.main.data.staff.Profession
import com.android.main.data.staff.StaffMovieDto
import com.android.main.databinding.PersonFilmographyFragmentBinding
import com.android.main.extensions.FromDpToPx
import com.android.main.presentation.decorator.VerticalItemDecorator
import com.android.main.presentation.movie.MovieDetailFragment.Companion.KEY_MOVIE_ID
import com.android.main.presentation.textNumRadioGroup.TextNumRadioButton

class PersonFilmographyFragment: Fragment() {

    private var _binding: PersonFilmographyFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel = PersonalFilmographyModelView()
    private lateinit var listAdapter: PersonFilmographyListAdapter

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
        arguments?.getString(KEY_PERSON_NAME)?.let {
            binding.personName.text = it
        }
        listAdapter = PersonFilmographyListAdapter {
            findNavController().navigate(
                R.id.action_personFilmographyFragment_to_movieDetailFragment,
                bundleOf(KEY_MOVIE_ID to it)
            )
        }
        with(binding.recyclerList) {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(this@PersonFilmographyFragment.context)

            addItemDecoration(VerticalItemDecorator(context, 8))
            setHasFixedSize(true)
        }
        arguments?.getBoolean(KEY_PERSON_GENDER)?.let {
            createButtons(it)
        }
    }

    private fun createButtons(isMale: Boolean) {
            binding.buttonsRole.let { radioGroup ->
                radioGroup.setOnChangeListener { id ->
                    bindData(viewModel.getDataByProfession(Profession.values()[id]))
                }

                viewModel.labels.forEach { (profession, i) ->
                    val radio = TextNumRadioButton(requireContext())

                    radioGroup.addView(radio.apply {
                        id = profession.ordinal
                        val resId = when (profession) {
                            Profession.ACTOR -> {
                                if (isMale) R.string.profession_actor_male else R.string.profession_actor_female
                            }

                            Profession.HIMSELF -> R.string.profession_himself
                            Profession.HERSELF -> R.string.profession_herself
                            Profession.COMPOSER -> R.string.profession_composer
                            Profession.DESIGN -> R.string.profession_design
                            Profession.PRODUCER -> R.string.profession_producer
                            Profession.DIRECTOR -> R.string.profession_director
                            Profession.EDITOR -> R.string.profession_editor
                            Profession.OPERATOR -> R.string.profession_operator
                            Profession.WRITER -> R.string.profession_writer
                            Profession.TRANSLATOR -> R.string.profession_writer
                            Profession.VOICE_DIRECTOR -> R.string.profession_voice_director
                            Profession.HRONO_TITR_MALE -> R.string.profession_hrono_titr_male
                            Profession.HRONO_TITR_FEMALE -> R.string.profession_hrono_titr_female
                        }

                        setContent(resources.getString(resId), i)
                    })
                    if (radioGroup.checked == View.NO_ID) {
                        radioGroup.setChecked(profession.ordinal)
                    }
                }
            }
        }

    private fun bindData(list: List<StaffMovieDto>) {
        listAdapter.setData(list)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    companion object {
        val KEY_FILM_LIST = "filmList"
        val KEY_PERSON_NAME = "personName"
        val KEY_PERSON_GENDER = "personGender"
    }
}