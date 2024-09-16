package com.app.jetpackcomposedemo.ui.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.jetpackcomposedemo.model.LoginCredentials
import com.app.jetpackcomposedemo.model.Todo
import com.app.jetpackcomposedemo.model.User
import com.app.jetpackcomposedemo.remote.api.ApiInterface
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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
                _userState.value = user
            } catch (e: Exception) {
                e.printStackTrace()  // Handle the error properly
            }
        }
    }

    suspend fun createUser(newUser: User): Boolean {
        try {
            val response = api.registerUser(newUser)
            if (response.status == HttpStatusCode.Created) {
                return true
            }
        } catch (e: Exception) {
            return false
        }
        return false

    }

    suspend fun loginUser(cred: LoginCredentials): User = api.loginUser(cred)

//    suspend fun getTodos(userId: String):Todo = api.getTODOs("1")

    var todoListResponse:List<Todo> by  mutableStateOf(listOf())

    fun getTodos(userId: String){
        viewModelScope.launch{
            val jsonString = api.getTODOs(userId)
            Log.i("TAGList", "getTodos: $jsonString")

            val todo = Gson().fromJson<Todo>(jsonString, Todo::class.java)

            todoListResponse = try{ listOf(todo) }catch (e:Exception){ emptyList() }
        }
    }


}
