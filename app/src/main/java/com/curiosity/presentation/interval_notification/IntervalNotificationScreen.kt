package com.curiosity.presentation.interval_notification

/**
 * @author matteooriggi
 */

import android.util.Log
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.curiosity.presentation.interval_notification.content.IntervalNotificationContent
import com.curiosity.presentation.interval_notification.content.IntervalNotificationError

/**
 * Composable function that represents the interval notification screen of the application.
 *
 * This function is responsible for displaying the user interval UI.
 */
@Composable
fun IntervalNotificationScreen(
    navController: NavController,
    viewModel: IntervalNotificationViewModel = hiltViewModel(),
){

    val state by viewModel.state.collectAsState()
    val isMinutes by viewModel.isMinutes.collectAsState()
    val interval by viewModel.interval.collectAsState()

    Log.d("IntervalNotificationScreen", "isMinutes: $isMinutes, interval: $interval")

    when {
        state.isLoading -> {
            CircularProgressIndicator()
        }
        state.updateCurrentUserIntervalNotificationSuccess -> {
            LaunchedEffect(Unit) {
                navController.popBackStack()
            }
        }
        state.loadCurrentUserIntervalNotificationError != null -> {
            IntervalNotificationError(
                navController = navController,
                state = state,
                error = state.loadCurrentUserIntervalNotificationError!!
            )
        }
        state.updateCurrentUserIntervalNotificationError != null -> {
            IntervalNotificationError(
                navController = navController,
                state = state,
                error = state.updateCurrentUserIntervalNotificationError!!
            )
        }
        else -> {
            IntervalNotificationContent(
                navController = navController,
                viewModel = viewModel,
                isMinutes = isMinutes,
                interval = interval,
                state = state
            )
        }
    }
}

@Preview
@Composable
fun IntervalNotificationScreenPreview(){
    IntervalNotificationScreen(
        navController = rememberNavController(),
        viewModel = hiltViewModel()
    )
}