package com.app.jetpackcomposedemo.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun ProfileScreen(navController: NavController, userId: Int?) {
    if (userId != null) {
        Text("Profile Screen for User ID: $userId")
    } else {
        Text("Profile Screen with no User ID")
    }
}