package com.example.users.presentation

sealed class UsersEvent {
    data object ErrorScreenTryAgainClicked : UsersEvent()
    data object ErrorItemRetryClicked : UsersEvent()
    data object ResetRetry : UsersEvent()
    data object PullToRefresh : UsersEvent()
    data object ResetRefresh : UsersEvent()
}