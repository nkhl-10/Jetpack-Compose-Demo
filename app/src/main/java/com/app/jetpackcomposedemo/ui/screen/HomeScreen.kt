package com.app.jetpackcomposedemo.ui.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.app.jetpackcomposedemo.navigation.NavigationItem


@Composable
fun HomeScreen(navController: NavController) {
    Button(
        onClick = { navController.navigate(NavigationItem.Login.route) },
        Modifier.fillMaxWidth()
    ) {
        Text("Go to Login")
    }
}