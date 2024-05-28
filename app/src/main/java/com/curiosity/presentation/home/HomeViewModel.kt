package com.curiosity.presentation.home

/**
 * @author matteooriggi
 */

import androidx.lifecycle.ViewModel
import com.curiosity.presentation.on_boarding.OnBoardingStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * ViewModel class for managing the state of the Home screen.
 *
 * This class holds the state of the Home screen using a StateFlow and provides
 * a way to observe changes to that state.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(

): ViewModel() {

    private val _state = MutableStateFlow(HomeStates())
    val state: StateFlow<HomeStates> = _state.asStateFlow()

    fun updateStateValue(newState: HomeStates){
        _state.value = newState
    }
}