package com.curiosity.presentation.home

/**
 * @author matteooriggi
 */

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curiosity.domain.model.CuriosityData
import com.curiosity.domain.model.Resource
import com.curiosity.domain.model.User
import com.curiosity.domain.use_cases.DiscardCuriosityUseCase
import com.curiosity.domain.use_cases.GetCuriosityUseCase
import com.curiosity.domain.use_cases.KnowCuriosityUseCase
import com.curiosity.domain.use_cases.LoadCurrentUserUseCase
import com.curiosity.domain.use_cases.NotKnowCuriosityUseCase
import com.curiosity.presentation.areas_of_interest.AreasOfInterestStates
import com.curiosity.presentation.on_boarding.OnBoardingStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel class for managing the state of the Home screen.
 *
 * This class holds the state of the Home screen using a StateFlow and provides
 * a way to observe changes to that state.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loadCurrentUserUseCase: LoadCurrentUserUseCase,
    private val getCuriosityUseCase: GetCuriosityUseCase,
    private val knowCuriosityUseCase: KnowCuriosityUseCase,
    private val notKnowCuriosityUseCase: NotKnowCuriosityUseCase,
    private val discardCuriosityUseCase: DiscardCuriosityUseCase
): ViewModel() {

    private val _state = MutableStateFlow(HomeStates())
    val state: StateFlow<HomeStates> = _state.asStateFlow()

    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> = _user.asStateFlow()

    private val _curiosity = MutableStateFlow(CuriosityData())
    val curiosity: StateFlow<CuriosityData> = _curiosity.asStateFlow()

    fun updateStateValue(newState: HomeStates){
        _state.value = newState
    }

    fun updateUserValue(newState: User){
        _user.value = newState
    }

    fun updateCuriosityValue(newValue: CuriosityData){
        _curiosity.value = newValue
    }

    init {
        getUser()
        getCuriosity()
    }

    private fun getUser(){
        viewModelScope.launch {
            val flow = loadCurrentUserUseCase()
            flow.onEach { resource ->
                when(resource){
                    is Resource.Loading -> {
                        _state.value = HomeStates(isLoading = true)
                    }
                    is Resource.Success -> {
                        resource.data?.let {
                            updateUserValue(it)
                            _state.value = HomeStates(loadUserSuccess = true)
                        } ?: run {
                            _state.value = HomeStates(loadUserError = resource.message)
                        }
                    }
                    is Resource.Error -> {
                        _state.value = HomeStates(loadUserError = resource.message)
                    }
                }
            }.launchIn(this)
        }
    }

    fun getCuriosity(){
        viewModelScope.launch {
            val flow = getCuriosityUseCase()
            flow.onEach { resource ->
                when(resource){
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(curiosityIsLoading = true)
                    }
                    is Resource.Success -> {
                        resource.data?.let {
                            updateCuriosityValue(it)
                            _state.value =  _state.value.copy(curiosityIsLoading = false, loadCurrentCuriositySuccess = true)
                        } ?: run {
                            _state.value =  _state.value.copy(curiosityIsLoading = false, loadCurrentCuriosityError = resource.message)
                        }
                    }
                    is Resource.Error -> {
                        _state.value =  _state.value.copy(curiosityIsLoading = false, loadCurrentCuriosityError = resource.message)
                    }
                }
            }.launchIn(this)
        }
    }

    fun knowCuriosity(){
        viewModelScope.launch {
            val flow = knowCuriosityUseCase(_user)
            flow.onEach { resource ->
                when(resource){
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        resource.data?.let {
                            updateUserValue(it)
                        }
                    }
                    is Resource.Error -> {}
                }
            }.launchIn(this)
        }
        getCuriosity()
    }

    fun notKnowCuriosity(){
        viewModelScope.launch {
            val flow = notKnowCuriosityUseCase(_user)
            flow.onEach { resource ->
                when(resource){
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        resource.data?.let {
                            updateUserValue(it)
                        }
                    }
                    is Resource.Error -> {}
                }
            }.launchIn(this)
        }
        getCuriosity()
    }

    fun discardCuriosity(){
        viewModelScope.launch {
            val flow = discardCuriosityUseCase(_user)
            flow.onEach { resource ->
                when(resource){
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        resource.data?.let {
                            updateUserValue(it)
                        }
                    }
                    is Resource.Error -> {}
                }
            }.launchIn(this)
        }
        getCuriosity()
    }
}