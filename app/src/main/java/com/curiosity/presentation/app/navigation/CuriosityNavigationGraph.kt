package com.curiosity.presentation.app.navigation

/**
 * @author matteooriggi
 */

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.curiosity.presentation.app.routes.Routes
import com.curiosity.presentation.intro.IntroScreen
import com.curiosity.presentation.on_boarding.OnBoardingScreen
import com.curiosity.presentation.profile.ProfileScreen
import com.curiosity.presentation.sign_in.SignInScreen
import com.curiosity.presentation.sign_up.SignUpScreen

/**
 * Composable function that sets up the navigation graph for the Curiosity app.
 *
 * This function creates and remembers a NavController and defines the navigation graph
 * with the start destination set to "main".
 */
@Composable
fun CuriosityNavigationGraph() {
    // Create and remember the NavController used for navigation within the app
    val navigationController = rememberNavController()

    // Define the navigation graph
    NavHost(
        navController = navigationController,
        startDestination = Routes.OnBoardingScreen.route
    ) {

        // Define singular node of the Navigation Graph
        composable(
            route = Routes.IntroScreen.route
        ){
            IntroScreen(navController = navigationController)
        }

        composable(
            route = Routes.SignInScreen.route
        ){
            SignInScreen(navController = navigationController)
        }

        composable(
            route = Routes.SignUpScreen.route
        ){
            SignUpScreen(navController = navigationController)
        }

        composable(
            route = Routes.ProfileScreen.route
        ){
            ProfileScreen(navController = navigationController)
        }

        composable(
            route = Routes.OnBoardingScreen.route
        ){
            OnBoardingScreen(navController = navigationController)
        }
    }
}