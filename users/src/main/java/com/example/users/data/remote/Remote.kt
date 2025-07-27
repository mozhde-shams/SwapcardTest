package com.example.users.data.remote

import com.example.users.data.JsonUsersList
import javax.inject.Inject

class Remote @Inject constructor(
    private val serviceGenerator: ServiceGenerator
) {
    suspend fun getUsersList(
        page: Int,
    ): JsonUsersList =
        serviceGenerator
            .usersService
            .getUsers(
                page = page
            )
}