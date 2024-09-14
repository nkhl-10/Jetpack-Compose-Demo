package com.app.jetpackcomposedemo.navigation

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.app.jetpackcomposedemo.ui.screen.LoginScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.app.jetpackcomposedemo.ui.screen.HomeScreen
import com.app.jetpackcomposedemo.ui.screen.ProfileScreen

@Composable
fun AppNavHost(navController: NavHostController, startDestination: String = NavigationItem.Home.route) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(NavigationItem.Home.route) {
            HomeScreen(navController)
        }
        composable(
            route = NavigationItem.Login.route + "/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.IntType })

        ) {
            val userId = it.arguments?.getInt("userId")
            LoginScreen(navController,userId)
        }
        composable(
            route = NavigationItem.Profile.route+"/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.IntType })  // Expecting an Int
        ) {
            val userId = it.arguments?.getInt("userId")
            ProfileScreen(navController, userId )
        }
    }
}

