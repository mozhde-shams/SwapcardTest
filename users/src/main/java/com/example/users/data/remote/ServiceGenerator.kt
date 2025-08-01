package com.example.users.data.remote

import retrofit2.Retrofit
import javax.inject.Inject

class ServiceGenerator @Inject constructor(
    private val retrofit: Retrofit
) {
    val usersService: UsersService = retrofit.create(UsersService::class.java)
}