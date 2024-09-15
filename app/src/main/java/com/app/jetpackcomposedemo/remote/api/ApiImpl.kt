package com.app.jetpackcomposedemo.remote.api

import com.app.jetpackcomposedemo.model.User
import com.app.jetpackcomposedemo.model.UserResponse
import com.app.jetpackcomposedemo.ui.utils.USER_URL
import io.ktor.client.features.get
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ApiImpl:ApiInterface {

    private val api = KtorClient.client

    override suspend fun getUser(userId: Int): User {
        return api.get(USER_URL+ userId)
    }


    override suspend fun registerUser(newUser: User): HttpResponse {
        return api.post(USER_URL) {
            contentType(ContentType.Application.Json)
            body = newUser
        }
    }

    override suspend fun loginUser(user: User): User {
            return api.get(USER_URL){

            }
    }
}