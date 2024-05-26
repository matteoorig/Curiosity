package com.curiosity.presentation.on_boarding

/**
 * @author matteooriggi
 */

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curiosity.domain.model.CuriosityAreasOfInterestItemData
import com.curiosity.domain.model.Resource
import com.curiosity.domain.use_cases.LoadAreasCategoriesUseCase
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
    private val loadAreasCategoriesUseCase: LoadAreasCategoriesUseCase,
    private val context: Context
): ViewModel()  {

    init {
        loadAreasOfInterestCategories()
    }

    private val _state = MutableStateFlow(OnBoardingStates())
    val state: StateFlow<OnBoardingStates> = _state.asStateFlow()

    private var _listAreasOfInterest = MutableStateFlow<List<CuriosityAreasOfInterestItemData>>(emptyList())
    private var _selectedListAreasOfInterest = MutableStateFlow<List<CuriosityAreasOfInterestItemData>>(emptyList())

    private val _isMinutes = MutableStateFlow(false)
    val isMinutes: StateFlow<Boolean> = _isMinutes.asStateFlow()

    private val _interval = MutableStateFlow(1)
    val interval: StateFlow<Int> = _interval.asStateFlow()

    fun updateStateValue(newState: OnBoardingStates){
        _state.value = newState
    }

    fun getAreasOfInterest(): List<CuriosityAreasOfInterestItemData> {
        return _listAreasOfInterest.value
    }

    fun updateIsMinutesValue(newValue: Boolean){
        _isMinutes.value = newValue
    }

    fun updateIntervalValue(newValue: Int){
        _interval.value = newValue
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

    fun confirmSelectionAreasOfInterest() {
        val selectedItems = _listAreasOfInterest.value.filter { it.isClicked }

        if(selectedItems.isEmpty()){
            Toast.makeText(
                context,
                "You must select at least one area of interest"
                , Toast.LENGTH_LONG
            ).show()
        }else{
            _selectedListAreasOfInterest.value = selectedItems
            _state.value = OnBoardingStates(onSelectAreasSuccess = true)
        }
    }

    fun confirmSelectionIntervalNotification(){
        Log.d("confirmSelectionIntervalNotification", "" + _isMinutes.value + " | " + _interval.value)
    }


}