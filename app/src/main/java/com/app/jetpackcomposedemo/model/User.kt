package com.app.jetpackcomposedemo.model

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val  list :List<User>
)

@Serializable
data class User(
    val createdAt: String,
    val name: String,
    val avatar: String,
    val email: String,
    val phoneno: String,
    val password: String,
    val address: String,
    val contry: String,
    val contry_code: String,
    val id: String
)
