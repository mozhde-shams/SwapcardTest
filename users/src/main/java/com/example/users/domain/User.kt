package com.example.users.domain

import kotlinx.coroutines.flow.Flow

data class User(
    val firstName: String?,
    val lastName: String?,
    val picture: String?,
    val id: String?,
    val isBookmarked: Flow<Boolean>,
)