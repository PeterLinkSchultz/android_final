package com.android.main.presentation.person

import android.util.Log
import androidx.lifecycle.ViewModel
import com.android.main.data.staff.Profession
import com.android.main.data.staff.StaffMovieDto

class PersonalFilmographyModelView: ViewModel() {

    private var data: List<StaffMovieDto> = listOf()
    private val _labels: MutableMap<Profession, Int> = mutableMapOf()
    val labels get() = _labels

    fun setData(newData: List<StaffMovieDto>) {
        data = newData
        data.forEach { item ->
            _labels[item.professionKey].let {
                if (it == null) {
                    _labels[item.professionKey] = 1
                } else {
                    _labels[item.professionKey] = it.plus(1)
                }
            }
        }
    }
}