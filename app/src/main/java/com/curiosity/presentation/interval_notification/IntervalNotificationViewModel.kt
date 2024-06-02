package com.curiosity.presentation.interval_notification

/**
 * @author matteooriggi
 */

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curiosity.domain.model.Resource
import com.curiosity.domain.use_cases.LoadCurrentUserUseCase
import com.curiosity.domain.use_cases.UpdateCurrentUserIntervalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IntervalNotificationViewModel @Inject constructor(
    private val loadCurrentUserUseCase: LoadCurrentUserUseCase,
    private val updateCurrentUserIntervalUseCase: UpdateCurrentUserIntervalUseCase
): ViewModel() {

    private val _state = MutableStateFlow(IntervalNotificationStates())
    val state: StateFlow<IntervalNotificationStates> = _state.asStateFlow()

    private val _isMinutes = MutableStateFlow(false)
    val isMinutes: StateFlow<Boolean> = _isMinutes.asStateFlow()

    private val _interval = MutableStateFlow(1)
    val interval: StateFlow<Int> = _interval.asStateFlow()

    fun updateStateValue(newState: IntervalNotificationStates){
        _state.value = newState
    }

    fun updateIsMinutesValue(newValue: Boolean){
        _isMinutes.value = newValue
    }

    fun updateIntervalValue(newValue: Int){
        _interval.value = newValue
    }
    init {
        getUser()
    }

    private fun getUser(){
        viewModelScope.launch {
            val flow = loadCurrentUserUseCase()
            flow.onEach { resource ->
                when(resource){
                    is Resource.Loading -> {
                        _state.value = IntervalNotificationStates(isLoading = true)
                    }
                    is Resource.Success -> {
                        resource.data?.let {
                            _isMinutes.value = it.isMinutes
                            _interval.value = it.interval
                            _state.value = IntervalNotificationStates(loadCurrentUserIntervalNotificationSuccess = true)
                        } ?: run {
                            _state.value = IntervalNotificationStates(loadCurrentUserIntervalNotificationError = resource.message)
                        }
                    }
                    is Resource.Error -> {
                        _state.value = IntervalNotificationStates(loadCurrentUserIntervalNotificationError = resource.message)
                    }
                }
            }.launchIn(this)
        }
    }

    fun confirmSelectionIntervalNotification(){
        viewModelScope.launch {
            val flow = updateCurrentUserIntervalUseCase(_isMinutes.value, _interval.value)
            flow.onEach { resource ->
                when(resource){
                    is Resource.Loading -> {
                        _state.value = IntervalNotificationStates(isLoading = true)
                    }
                    is Resource.Success -> {
                        _state.value = IntervalNotificationStates(updateCurrentUserIntervalNotificationSuccess = true)
                    }
                    is Resource.Error -> {
                        _state.value = IntervalNotificationStates(updateCurrentUserIntervalNotificationError = resource.message)
                    }
                }
            }.launchIn(this)
        }
    }
}