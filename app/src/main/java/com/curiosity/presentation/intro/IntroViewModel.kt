package com.curiosity.presentation.intro

/**
 * @author matteooriggi
 */

import android.Manifest
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curiosity.R
import com.curiosity.domain.model.Resource
import com.curiosity.domain.use_cases.ExistCurrentUserUseCase
import com.curiosity.domain.use_cases.LogoutCurrentUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel class for managing the state of the intro screen.
 *
 * This class holds the state of the intro screen using a StateFlow and provides
 * a way to observe changes to that state.
 */
@HiltViewModel
class IntroViewModel @Inject constructor(
    private val existCurrentUserUseCase: ExistCurrentUserUseCase,
    private val context: Context
): ViewModel() {

    private val _state = MutableStateFlow(IntroStates())
    val state: StateFlow<IntroStates> = _state.asStateFlow()

    fun setStateValue(newValue: IntroStates){
        _state.value = newValue
    }
    init {
        val hasNotificationPermission: Boolean

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            hasNotificationPermission = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED

            Log.d("POST_NOTIFICATIONS_CURIOSITY", "Stato del permmesso: $hasNotificationPermission")
        } else {
            hasNotificationPermission = true
        }

        if(hasNotificationPermission){
            _state.value = _state.value.copy(hasNotificationPermissionSuccessful = hasNotificationPermission)
            checkUserExist()
        }else {
            _state.value = _state.value.copy(hasNotificationPermissionSuccessful = hasNotificationPermission, hasNotificationPermissionError = "To use Curiosity you must give notifications permission. Go to settings and change permission value")
        }
    }

    fun confirmNotificationPermission(response: Boolean){
        if(response){
            // mando una notifica di prova
            showNotification()
            _state.value = IntroStates(hasNotificationPermissionSuccessful = true)
            checkUserExist()
        }else{
            _state.value = IntroStates(hasNotificationPermissionSuccessful = false, hasNotificationPermissionError = "To use Curiosity you must give notifications permission. Go to settings and change permission value")
        }
    }

    private fun showNotification(){
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(context, "curiosity_identifier")
            .setContentText("Prova content text")
            .setContentTitle("hello world")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()
        notificationManager.notify(1, notification)
    }

    private fun checkUserExist() {
        viewModelScope.launch {
            existCurrentUserUseCase().onEach { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            currentUserExist = resource.data ?: false
                        )
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            currentUserExistError = resource.message
                        )
                    }
                }
            }.launchIn(this)
        }
    }
}