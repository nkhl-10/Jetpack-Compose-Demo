package com.app.jetpackcomposedemo.ui.screen

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.jetpackcomposedemo.ui.components.AppBar
import com.app.jetpackcomposedemo.ui.navigation.HomeNavHost
import com.app.jetpackcomposedemo.ui.navigation.HomeTabItem
import com.app.jetpackcomposedemo.ui.tabs.BottomNavigationBar


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {
    val navControllerBottom = rememberNavController()
    // Observe the current backstack entry as state
    val navBackStackEntry by navControllerBottom.currentBackStackEntryAsState()
    // Get the current route
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navControllerBottom) },
        topBar = { AppBar(title = when (currentRoute) {
                    HomeTabItem.Home.route -> "Home"
                    HomeTabItem.Search.route -> "Search"
                    HomeTabItem.Profile.route -> "Profile"
                    else -> "Demo App"
                }) }
    ) { HomeNavHost(navController = navControllerBottom) }
}

