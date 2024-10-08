package com.app.jetpackcomposedemo.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.app.jetpackcomposedemo.model.LoginCredentials
import com.app.jetpackcomposedemo.remote.api.ApiImpl
import com.app.jetpackcomposedemo.remote.api.ApiInterface
import com.app.jetpackcomposedemo.remote.sharedPreferences.USER
import com.app.jetpackcomposedemo.remote.sharedPreferences.getStringData
import com.app.jetpackcomposedemo.remote.sharedPreferences.saveBooleanData
import com.app.jetpackcomposedemo.remote.sharedPreferences.saveStringData
import com.app.jetpackcomposedemo.ui.navigation.NavigationItem
import com.app.jetpackcomposedemo.ui.viewModel.UserViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController:NavController) {
    val userApi: ApiInterface = ApiImpl()
    val viewModel = UserViewModel(userApi)
    val context = LocalContext.current

    // State variables for the inputs
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var passwordVisible by remember { mutableStateOf(false) }

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

            // Username input field
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                isError = errorMessage != null && email.isEmpty(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)

            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password input field
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                isError = errorMessage != null && password.isEmpty(),
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, contentDescription = if (passwordVisible) "Hide password" else "Show password")
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)


            )

            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Create Account", modifier = Modifier.clickable {
                    navController.navigate(NavigationItem.Register.route)
                })
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
                    if (email.isEmpty() || password.isEmpty()) {
                        errorMessage = "Please enter both username and password"
                    } else {
                        isLoading = true
                        // Simulate login process
                        MainScope().launch {
                            delay(2000) // Simulate network delay
                            isLoading = false
                            val loginData = viewModel.loginUser(LoginCredentials(email, password))
                                if (loginData != null){
                                    context.saveBooleanData(USER.UserIsLogged.name,true)
                                    context.saveStringData(USER.USER_NAME.name,loginData.name)
                                    context.saveStringData(USER.USER_ID.name,loginData.id)
                                    navController.navigate(NavigationItem.Home.createRoute(context.getStringData(USER.USER_ID.name,"0").toInt())) {
                                        popUpTo(NavigationItem.Login.route) { inclusive = true }
                                    }
                                }else{
                                    isLoading = false
                                    errorMessage = "Invalid credentials"
                                }


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
