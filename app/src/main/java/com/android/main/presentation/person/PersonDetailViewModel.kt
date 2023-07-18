package com.android.main.presentation.person

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.main.data.staff.StaffDto
import com.android.main.presentation.LoadDataState
import com.example.main.data.KinopoiskRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class PersonDetailViewModel @Inject constructor(private val kinopoiskRepository: KinopoiskRepository): ViewModel() {

    private val liveData = MutableLiveData<LoadDataState<StaffDto>>()
    val data: LiveData<LoadDataState<StaffDto>>
        get() = liveData

    fun loadData(staffId: Int) {
        viewModelScope.launch {
            val staffDetail = async { kinopoiskRepository.getStaffById(staffId) }

            liveData.value = LoadDataState.Loading()
            runCatching {
                staffDetail.await()
            }.onSuccess {
                liveData.value = LoadDataState.Success(it)
            }.onFailure {
                liveData.value = LoadDataState.Failure()
            }
        }
    }
}