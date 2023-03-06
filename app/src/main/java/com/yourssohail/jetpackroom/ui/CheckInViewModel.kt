package com.yourssohail.jetpackroom.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yourssohail.jetpackroom.data.CheckIn
import com.yourssohail.jetpackroom.repository.CheckInRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckInViewModel @Inject constructor(
    private val repository: CheckInRepository
):ViewModel() {

    private val _checkIns = MutableStateFlow(emptyList<CheckIn>())
    val checkIns: StateFlow<List<CheckIn>> = _checkIns

    init {
        viewModelScope.launch {
            _checkIns.emit(repository.getAllItems())
        }
    }

    fun insertCheckIn(checkIn: CheckIn) {
        viewModelScope.launch {
            repository.insertCheckIn(checkIn)
            _checkIns.emit(repository.getAllItems())
        }
    }
}