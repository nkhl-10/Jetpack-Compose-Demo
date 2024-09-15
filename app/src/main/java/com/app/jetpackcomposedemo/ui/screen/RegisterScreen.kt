package com.app.jetpackcomposedemo.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.app.jetpackcomposedemo.model.LoginCredentials
import com.app.jetpackcomposedemo.model.User
import com.app.jetpackcomposedemo.remote.api.ApiImpl
import com.app.jetpackcomposedemo.remote.api.ApiInterface
import com.app.jetpackcomposedemo.ui.navigation.NavigationItem
import com.app.jetpackcomposedemo.ui.viewModel.UserViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavHostController) {
    val userApi: ApiInterface = ApiImpl()
    val viewModel = UserViewModel(userApi)


// State variables for the inputs
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNo by remember { mutableStateOf("") }
    var paasword by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

// UI Layout for Login Screen
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {

             OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth(),
                isError = errorMessage != null && name.isEmpty(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                isError = errorMessage != null && email.isEmpty(),
                singleLine = true
            )


            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = phoneNo,
                onValueChange = { phoneNo = it },
                label = { Text("Phone No") },
                modifier = Modifier.fillMaxWidth(),
                isError = errorMessage != null && phoneNo.isEmpty(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.height(16.dp))


            // Password input field
            OutlinedTextField(
                value = paasword,
                onValueChange = { paasword = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                isError = errorMessage != null && paasword.isEmpty(),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Create Account")
                Text(text = "Forget Password")
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Show error message if present
            errorMessage?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            // Login button
            Button(
                onClick = {
                    errorMessage = null
                    if (name.isEmpty() || paasword.isEmpty() || phoneNo.isEmpty() || email.isEmpty()) {
                        errorMessage = "Please enter details"
                    } else {
                        isLoading = true
                        // Simulate login process
                        MainScope().launch {
                            delay(2000) // Simulate network delay
                                isLoading = false
                            val user= User(
                                email = email,
                                password = paasword,
                                name = name,
                                phoneno = phoneNo,
                                address = "null",
                                avatar = "null",
                                contry = "null",
                                contry_code = "null",
                                createdAt = "null",
                                id = (0..10).random().toString()
                            )

                            if ( viewModel.createUser(user)){
                                navController.navigate(NavigationItem.Home.createRoute(123)) {
                                    popUpTo(NavigationItem.Login.route) { inclusive = true }
                                }
                            }else{
                                isLoading = false
                                errorMessage = "Invalid credentials"
                            }



                              /*  if (viewModel.createUser(user)) {
                                    navController.navigate(NavigationItem.Home.route) {
                                        popUpTo(NavigationItem.Login.route) { inclusive = true }
                                    }
                                } else {
                                    isLoading = false
                                    errorMessage = "Invalid credentials"
                                }*/


                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Login", style = TextStyle(fontSize = 18.sp))
            }

            // Loading Indicator
            if (isLoading) {
                Spacer(modifier = Modifier.height(16.dp))
                CircularProgressIndicator()
            }
        }
    }
}
