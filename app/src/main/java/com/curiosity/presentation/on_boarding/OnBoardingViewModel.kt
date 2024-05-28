package com.curiosity.presentation.on_boarding

/**
 * @author matteooriggi
 */

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curiosity.data.model.Preferences
import com.curiosity.domain.model.CuriosityAreasOfInterestItemData
import com.curiosity.domain.model.Resource
import com.curiosity.domain.repository.DataRepository
import com.curiosity.domain.use_cases.LoadAreasCategoriesUseCase
import com.curiosity.domain.use_cases.UpdateCurrentUserPreferencesUseCase
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
    private val context: Context,
    private val updateCurrentUserPreferencesUseCase: UpdateCurrentUserPreferencesUseCase
): ViewModel()  {

    init {
        // Loading the categories of the areas of interest present in firestore. This allows the scalability of the application.
        loadAreasOfInterestCategories()
    }

    private val _state = MutableStateFlow(OnBoardingStates())
    val state: StateFlow<OnBoardingStates> = _state.asStateFlow()

    // Contains the categories present in firestore.
    private var _listAreasOfInterest = MutableStateFlow<List<CuriosityAreasOfInterestItemData>>(emptyList())
    // Contains the categories selected by user.
    private var _selectedListAreasOfInterest = MutableStateFlow<List<CuriosityAreasOfInterestItemData>>(emptyList())

    // Allows to define whether the interval is in hours or minutes.
    private val _isMinutes = MutableStateFlow(false)
    val isMinutes: StateFlow<Boolean> = _isMinutes.asStateFlow()

    // Contains the numeric value of the time interval.
    private val _interval = MutableStateFlow(1)
    val interval: StateFlow<Int> = _interval.asStateFlow()

    // Method used by the composable function to obtain the list of areas of interest and the related icons present in firestore.
    fun getAreasOfInterest(): List<CuriosityAreasOfInterestItemData> {
        return _listAreasOfInterest.value
    }

    // Method used by the composable function to get all the selected areas of interest in a single string.
    fun getAreasOfInterestValuesFormatted(): String {
        var result = ""
        _selectedListAreasOfInterest.value.forEachIndexed { index, area ->
            Log.d("INDEX", index.toString())
            result += if(index < _selectedListAreasOfInterest.value.size - 1) area.value + ", " else area.value + ""
        }
        return result
    }

    // Method used by the composable function to obtain the interval value formatted for user.

    fun getIntervalFormattedValue(): String {
        return if(isMinutes.value) "" + _interval.value + " minutes" else "" + _interval.value + " hours"
    }

    fun updateStateValue(newState: OnBoardingStates){
        _state.value = newState
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
                        _state.value = OnBoardingStates(isLoading = true)
                    }
                    is Resource.Success -> {
                        _listAreasOfInterest.value = resource.data!!
                        _state.value = OnBoardingStates(isLoading = false, loadAreasOfInterestSuccess = true)
                    }
                    is Resource.Error -> {
                        _state.value = OnBoardingStates(isLoading = false, loadAreasOfInterestError = resource.message)
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
                "You must select at least one area of interest",
                Toast.LENGTH_LONG
            ).show()
        }else{
            _selectedListAreasOfInterest.value = selectedItems
            _state.value = OnBoardingStates(onSelectAreasSuccess = true)
        }
    }

    fun confirmSelectionIntervalNotification(){
        if(_interval.value == 0){
            Toast.makeText(
                context,
                "I'm sorry but don't be clever, you can't turn off notifications in this way.",
                Toast.LENGTH_LONG
            ).show()
        }else {
            _state.value = OnBoardingStates(onSelectIntervalSuccess = true)
        }
    }

    fun confirmSelectionExploreTheApp(){

        if(_selectedListAreasOfInterest.value.isEmpty() || _interval.value == 0){
            Toast.makeText(
                context,
                "I'm sorry but because an internal error we lost your choices. " +
                        "There will be the default ones. Don't worry, you can change it in any time.",
                Toast.LENGTH_LONG
            ).show()
        }else{

            val listOfPreferences: List<Preferences> = _selectedListAreasOfInterest.value.map { selectedArea ->
                Preferences(
                    preferenceValue = selectedArea.value
                )
            }
            viewModelScope.launch {
                val flow = updateCurrentUserPreferencesUseCase(listOfPreferences)
                flow.onEach { resource ->
                    when(resource){
                        is Resource.Loading -> {
                            _state.value = OnBoardingStates(isLoading = true)
                        }
                        is Resource.Success -> {
                            _state.value = OnBoardingStates(onSummarySuccess = true)
                        }
                        is Resource.Error -> {
                            _state.value = OnBoardingStates(onSummaryError = resource.message)
                        }
                    }
                }.launchIn(this)
            }
        }
    }


}