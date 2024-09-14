package com.app.jetpackcomposedemo.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.app.jetpackcomposedemo.navigation.NavigationItem


@Composable
fun HomeScreen(navController: NavController) {
    Column {
        Button(
            onClick = { navController.navigate(NavigationItem.Login.createRoute(123)) },
            Modifier.fillMaxWidth()
        ) {
            Text("Go to Login")
        }
        Button(
            onClick = { navController.navigate(NavigationItem.Profile.route+"/123") },
            Modifier.fillMaxWidth()
        ) {
            Text("Go to Login")
        }
    }

}