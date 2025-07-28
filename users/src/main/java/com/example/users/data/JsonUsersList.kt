package com.example.users.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JsonUsersList(
    @SerialName("results")
    val usersList: List<JsonUser>,
    val info: JsonInfo,
)

@Serializable
data class JsonUser(
    val name: JsonName,
    val picture: JsonPicture,
    val id: JsonId,
)

@Serializable
data class JsonName(
    @SerialName("first")
    val firstName: String?,
    @SerialName("last")
    val lastName: String?,
)

@Serializable
data class JsonPicture(
    val medium: String?,
)

@Serializable
data class JsonId(
    val value: String?,
)

@Serializable
data class JsonInfo(
    val page: Int,
)