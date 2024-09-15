package com.app.jetpackcomposedemo.ui.tabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.app.jetpackcomposedemo.remote.sharedPreferences.clearAllData
import com.app.jetpackcomposedemo.ui.navigation.NavigationItem

@Composable
fun ProfileTabScreen(navHostController: NavController) {
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = {
            context.clearAllData()
            navHostController.navigate(NavigationItem.Splash.route)
        })  {
            Text(text = "Logout", style = TextStyle(fontSize = 18.sp))
        }
    }
}