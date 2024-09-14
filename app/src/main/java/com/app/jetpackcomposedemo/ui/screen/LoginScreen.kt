package com.app.jetpackcomposedemo.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController, userId: Int?) {
    Text("Login Screen  $userId")
}