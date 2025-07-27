package com.example.users.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPagedUsersUseCase @Inject constructor(
    private val usersRepository: UsersRepository,
) {
    suspend operator fun invoke(): Flow<PagingData<User>> = usersRepository.getUsersList()
}