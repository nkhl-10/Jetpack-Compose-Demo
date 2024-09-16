package com.app.jetpackcomposedemo.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Todo(
    @SerialName("createdAt") val createdAt: String,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("id") val id: String
)