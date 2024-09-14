package com.app.jetpackcomposedemo.navigation

sealed class NavigationItem(val route: String) {
    object Home : NavigationItem(Screen.HOME.name)
    object Login : NavigationItem(Screen.LOGIN.name){
            fun createRoute(userId: Int) = "$route/$userId"
    }
    object Profile : NavigationItem(Screen.PROFILE.name)
}
