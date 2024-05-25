package com.curiosity.presentation.on_boarding

/**
 * @author matteooriggi
 */

import androidx.lifecycle.ViewModel
import com.curiosity.domain.model.CuriosityAreasOfInterestItemData
import com.curiosity.presentation.sign_in.SignInStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * ViewModel class for managing the state of the OnBoarding screen.
 *
 * This class holds the state of the OnBoarding screen using a StateFlow and provides
 * a way to observe changes to that state.
 */
@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    // TODO: implement getAreasCategories from LoadAreasCategories Use Case
): ViewModel()  {
    private val _state = MutableStateFlow(OnBoardingStates())
    val state: StateFlow<OnBoardingStates> = _state.asStateFlow()

    fun updateStateValue(newState: OnBoardingStates){
        _state.value = newState
    }

    fun getAreasOfInterest(): List<CuriosityAreasOfInterestItemData> {
        TODO("Not yet implemented")
    }


}