package com.example.user_detail.presentation

data class UserDetailsState(
    val userName: String = "",
    val userKey: String = "",
    val isBookmarked: Boolean = false
)