package com.app.jetpackcomposedemo.ui.screen

import android.annotation.SuppressLint
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.jetpackcomposedemo.ui.navigation.HomeNavHost
import com.app.jetpackcomposedemo.ui.navigation.HomeTabItem
import com.app.jetpackcomposedemo.ui.tabs.BottomNavigationBar


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {
    val navControllerBottom = rememberNavController()
    val currentRoute = navControllerBottom.currentBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navControllerBottom)
        },
        topBar = {
            AppBar(
                title = when (currentRoute) {
                    HomeTabItem.Home.route -> "Home"
                    HomeTabItem.Search.route -> "Search"
                    HomeTabItem.Profile.route -> "Profile"
                    else -> "Demo App"
                }
            )
        }
    ) {
        HomeNavHost(navController = navControllerBottom)
    }
}

@Composable
fun AppBar(title: String, onNavigationIconClick: () -> Unit = {}) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(Icons.Filled.Menu, contentDescription = "Menu")
            }
        },
        actions = {
            IconButton(onClick = onNavigationIconClick ) {
                Icon(Icons.Filled.Notifications, contentDescription = "Notifications")
            }
        }
    )
}
