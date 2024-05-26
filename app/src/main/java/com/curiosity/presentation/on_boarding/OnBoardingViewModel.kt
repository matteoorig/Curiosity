package com.curiosity.presentation.on_boarding

/**
 * @author matteooriggi
 */

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curiosity.domain.model.CuriosityAreasOfInterestItemData
import com.curiosity.domain.model.Resource
import com.curiosity.domain.use_cases.LoadAreasCategoriesUseCase
import com.curiosity.presentation.sign_in.SignInStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel class for managing the state of the OnBoarding screen.
 *
 * This class holds the state of the OnBoarding screen using a StateFlow and provides
 * a way to observe changes to that state.
 */
@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val loadAreasCategoriesUseCase: LoadAreasCategoriesUseCase
): ViewModel()  {

    init {
        loadAreasOfInterestCategories()
    }

    private val _state = MutableStateFlow(OnBoardingStates())
    val state: StateFlow<OnBoardingStates> = _state.asStateFlow()

    private var _listAreasOfInterest = MutableStateFlow<List<CuriosityAreasOfInterestItemData>>(emptyList())

    fun updateStateValue(newState: OnBoardingStates){
        _state.value = newState
    }

    fun getAreasOfInterest(): List<CuriosityAreasOfInterestItemData> {
        return _listAreasOfInterest.value
    }

    private fun loadAreasOfInterestCategories(){
        viewModelScope.launch {
            val flow = loadAreasCategoriesUseCase()
            flow.onEach { resource ->
                when(resource){
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }
                    is Resource.Success -> {
                        _listAreasOfInterest.value = resource.data!!
                        _state.value = _state.value.copy(isLoading = false, loadAreasOfInterestSuccess = true)
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(isLoading = false, loadAreasOfInterestError = resource.message)
                    }
                }

            }.launchIn(this)
        }
    }


}