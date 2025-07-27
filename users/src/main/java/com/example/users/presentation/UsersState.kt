package com.example.users.presentation

import androidx.paging.PagingData
import com.example.users.domain.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class UsersState(
    val needsRetry: Boolean = false,
    val needsRefresh: Boolean = false,
    val users: Flow<PagingData<User>> = emptyFlow(),
)