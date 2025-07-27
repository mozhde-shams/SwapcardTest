package com.example.users.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    suspend fun getUsersList(): Flow<PagingData<User>>
}