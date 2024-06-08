package com.curiosity.presentation.areas_of_interest

/**
 * @author matteooriggi
 */

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curiosity.domain.model.CuriosityAreasOfInterestItemData
import com.curiosity.domain.model.Preferences
import com.curiosity.domain.model.Resource
import com.curiosity.domain.use_cases.LoadAreasCategoriesUseCase
import com.curiosity.domain.use_cases.LoadCurrentUserUseCase
import com.curiosity.domain.use_cases.UpdateCurrentUserPreferencesUseCase
import com.curiosity.presentation.on_boarding.OnBoardingStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AreasOfInterestViewModel @Inject constructor(
    private val context: Context,
    private val loadAreasCategoriesUseCase: LoadAreasCategoriesUseCase,
    private val loadCurrentUserUseCase: LoadCurrentUserUseCase,
    private val updateCurrentUserPreferencesUseCase: UpdateCurrentUserPreferencesUseCase
): ViewModel() {

    private val _state = MutableStateFlow(AreasOfInterestStates())
    val state: StateFlow<AreasOfInterestStates> = _state.asStateFlow()

    // Contains the categories present in firestore.
    private var _listAreasOfInterest = MutableStateFlow<List<CuriosityAreasOfInterestItemData>>(emptyList())

    // Contains the categories selected by user.
    private var _selectedCurrentUserListAreasOfInterest = MutableStateFlow<List<Preferences>>(emptyList())

    init {
        _listAreasOfInterest = MutableStateFlow<List<CuriosityAreasOfInterestItemData>>(emptyList())
        _selectedCurrentUserListAreasOfInterest = MutableStateFlow<List<Preferences>>(emptyList())
        loadAreasOfInterestCategories()
        loadCurrentUserAreasOfInterestCategories()
    }

    fun updateStateValue(newState: AreasOfInterestStates){
        _state.value = newState
    }

    // Method used by the composable function to obtain the list of areas of interest previously selected by user.
    fun getAreasOfInterest(): List<CuriosityAreasOfInterestItemData> {
        _listAreasOfInterest.value.forEach { area ->
            _selectedCurrentUserListAreasOfInterest.value.forEach { selectedArea ->
                if(selectedArea.preferenceValue == area.value){
                    area.isClicked = true
                }
            }
        }
        return _listAreasOfInterest.value
    }

    fun confirmSelectionAreasOfInterest() {
        val selectedItems = _listAreasOfInterest.value.filter { it.isClicked }

        if(selectedItems.isEmpty()){
            Toast.makeText(
                context,
                "You must select at least one area of interest",
                Toast.LENGTH_LONG
            ).show()
        }else{
            val selectedPreferences: List<Preferences> = selectedItems.map { curiosityAreasOfInterestItemData ->
                Preferences(
                    preferenceValue = curiosityAreasOfInterestItemData.value
                )
            }
            viewModelScope.launch {
                val flow = updateCurrentUserPreferencesUseCase(selectedPreferences)
                flow.onEach { resource ->
                    when(resource){
                        is Resource.Loading -> {
                            _state.value = AreasOfInterestStates(isLoading = true)
                        }
                        is Resource.Success -> {
                            _state.value = AreasOfInterestStates(updateCurrentUserAreasOfInterestSuccess = true)
                        }
                        is Resource.Error -> {
                            _state.value = AreasOfInterestStates(updateCurrentUserAreasOfInterestError = resource.message)
                        }
                    }
                }.launchIn(this)
            }
        }
    }

    private fun loadAreasOfInterestCategories(){
        viewModelScope.launch {
            val flow = loadAreasCategoriesUseCase()
            flow.onEach { resource ->
                when(resource){
                    is Resource.Loading -> {
                        _state.value = AreasOfInterestStates(isLoading = true)
                    }
                    is Resource.Success -> {
                        resource.data?.let {
                            _listAreasOfInterest.value = it
                            _state.value = AreasOfInterestStates(loadAreasOfInterestSuccess = true)
                        } ?: run {
                            _state.value = AreasOfInterestStates(loadAreasOfInterestError = "I'm sorry, try again!")
                        }
                    }
                    is Resource.Error -> {
                        _state.value = AreasOfInterestStates(loadAreasOfInterestError = resource.message)
                    }
                }
            }.launchIn(this)
        }
    }

    private fun loadCurrentUserAreasOfInterestCategories(){
        viewModelScope.launch {
            val flow = loadCurrentUserUseCase()
            flow.onEach { resource ->
                when(resource){
                    is Resource.Loading -> {
                        _state.value = AreasOfInterestStates(isLoading = true)
                    }
                    is Resource.Success -> {
                        resource.data?.let {
                            _selectedCurrentUserListAreasOfInterest.value = it.preferences
                            _state.value = AreasOfInterestStates(loadCurrentUserAreasOfInterestSuccess = true)
                        } ?: run {
                            _state.value = AreasOfInterestStates(loadCurrentUserAreasOfInterestError = "I'm sorry, try again!")
                        }
                    }
                    is Resource.Error -> {
                        _state.value = AreasOfInterestStates(loadCurrentUserAreasOfInterestError = resource.message)
                    }
                }
            }.launchIn(this)
        }
    }
}