package com.app.jetpackcomposedemo.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector


sealed class HomeTabItem(val route: String, val icon: ImageVector, val label: String) {
    object Home : HomeTabItem(Tab.HOME_TAB.name, Icons.Filled.Home, "Home")
    object Search : HomeTabItem(Tab.SEARCH_TAB.name, Icons.Filled.Search, "Search")
    object Profile : HomeTabItem(Tab.PROFILE_TAB.name, Icons.Filled.Person, "Profile")
}