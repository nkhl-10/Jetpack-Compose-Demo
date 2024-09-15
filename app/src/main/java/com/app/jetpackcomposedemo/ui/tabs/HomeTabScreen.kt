package com.app.jetpackcomposedemo.ui.tabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.app.jetpackcomposedemo.remote.api.ApiImpl
import com.app.jetpackcomposedemo.remote.api.ApiInterface
import com.app.jetpackcomposedemo.ui.viewModel.UserViewModel

@Composable
fun HomeTabScreen() {
    val userApi: ApiInterface = ApiImpl()  // Creating an instance of KtorUserApi
    val viewModel = UserViewModel(userApi)  // Passing the API instance to ViewModel

    val user by viewModel.userState.collectAsState()

//    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//        Text("Home Tab")
//    }
    Column {
        if (user != null) {
            Text(text = "Name: ${user?.name}")
        } else {
            Button(onClick = { viewModel.fetchUser(1) }) {
                Text("Fetch User")
            }
        }
    }
}