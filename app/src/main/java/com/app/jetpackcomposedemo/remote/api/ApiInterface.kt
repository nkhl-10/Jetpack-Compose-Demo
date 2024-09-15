package com.app.jetpackcomposedemo.remote.api

import com.app.jetpackcomposedemo.model.LoginCredentials
import com.app.jetpackcomposedemo.model.User
import com.app.jetpackcomposedemo.model.UserResponse
import io.ktor.client.statement.HttpResponse

interface ApiInterface {
    suspend fun getUser(userId: Int): User
    suspend fun registerUser(newUser: User): HttpResponse
    suspend fun loginUser(cred :LoginCredentials) :User
}