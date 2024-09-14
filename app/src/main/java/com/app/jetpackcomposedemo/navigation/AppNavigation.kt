package com.app.jetpackcomposedemo.navigation

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.app.jetpackcomposedemo.ui.screen.LoginScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import com.app.jetpackcomposedemo.ui.screen.HomeScreen

@Composable
fun AppNavHost(navController: NavHostController, startDestination: String = NavigationItem.Home.route) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(NavigationItem.Home.route) {
            HomeScreen(navController)
        }
        composable(NavigationItem.Login.route) {
            LoginScreen(navController)
        }
    }
}

