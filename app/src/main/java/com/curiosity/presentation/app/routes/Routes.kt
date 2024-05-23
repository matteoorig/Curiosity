package com.curiosity.presentation.app.routes

/**
 * @author matteooriggi
 */

/**
 * Sealed class that defines the different routes in the application.
 *
 * Each route is represented as an object with a unique string value.
 *
 * @property route The string value representing the route.
 */
sealed class Routes(val route: String) {
    /**
     * Object representing the intro screen route.
     */
    object IntroScreen : Routes("intro_screen")

    /**
     * Object representing the intro screen route.
     */
    object SignInScreen : Routes("sign_in_screen")
}