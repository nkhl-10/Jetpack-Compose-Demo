package com.app.jetpackcomposedemo.ui.navigation

sealed class NavigationItem(val route: String) {
    object Home : NavigationItem(Screen.HOME.name)  fun createRoute(userId: Int) = "$route/$userId"
    object Login : NavigationItem(Screen.LOGIN.name)
    object Profile : NavigationItem(Screen.PROFILE.name)
    object Splash : NavigationItem(Screen.SPLASH.name)
}


