package com.app.jetpackcomposedemo.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.jetpackcomposedemo.model.LoginCredentials
import com.app.jetpackcomposedemo.model.User
import com.app.jetpackcomposedemo.remote.api.ApiInterface
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val api: ApiInterface) : ViewModel() {

    // State management using StateFlow
    private val _userState = MutableStateFlow<User?>(null)
    val userState: StateFlow<User?> = _userState

    fun fetchUser(userId: Int) {
        viewModelScope.launch {
            try {
                val user = api.getUser(userId)
                Log.i("DATAAPI", "fetchUser: $user")
                _userState.value = user
            } catch (e: Exception) {
                e.printStackTrace()  // Handle the error properly
            }
        }
    }

    suspend fun createUser(newUser: User):Boolean {
            try {
                val response = api.registerUser(newUser)
                if (response.status == HttpStatusCode.Created) {
                  return true
                }
            } catch (e: Exception) {
                return  false
            }
        return false

    }
    suspend fun loginUser(cred: LoginCredentials): User {
        return api.loginUser(cred) // This would be a suspend function
    }


}
