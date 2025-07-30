package com.example.user_detail.presentation

sealed class UserDetailsEvent {
    data class ToggleBookmarkClicked(val key: String) : UserDetailsEvent()
}