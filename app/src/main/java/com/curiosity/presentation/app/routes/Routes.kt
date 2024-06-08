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
     * Object representing the sign-in screen route.
     */
    object SignInScreen : Routes("sign_in_screen")

    /**
     * Object representing the sign-up screen route.
     */
    object SignUpScreen : Routes("sign_up_screen")

    /**
     * Object representing the profile screen route.
     */
    object ProfileScreen : Routes("profile_screen")

    /**
     * Object representing the on boarding screen route.
     */
    object OnBoardingScreen : Routes("on_boarding_screen")

    /**
     * Object representing the home screen route.
     */
    object HomeScreen : Routes("home_screen")

    /**
     * Object representing the areas of interest screen route.
     */
    object AreasOfInterestScreen : Routes("areas_of_interest_screen")

    /**
     * Object representing the interval notification screen route.
     */
    object IntervalNotification : Routes("interval_notification_screen")

    /**
     * Object representing the reset password screen route.
     */
    object ResetPassword : Routes("reset_password")
}