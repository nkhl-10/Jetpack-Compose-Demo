package com.app.jetpackcomposedemo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.jetpackcomposedemo.ui.tabs.HomeTabScreen
import com.app.jetpackcomposedemo.ui.tabs.ProfileTabScreen
import com.app.jetpackcomposedemo.ui.tabs.SearchTabScreen


@Composable
fun HomeNavHost(navController: NavHostController,mainNavController: NavController) {
    NavHost(navController = navController, startDestination = HomeTabItem.Home.route) {
        composable(HomeTabItem.Home.route) { HomeTabScreen() }
        composable(HomeTabItem.Search.route) { SearchTabScreen() }
        composable(HomeTabItem.Profile.route) { ProfileTabScreen(mainNavController) }
    }
}