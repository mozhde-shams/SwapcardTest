package com.example.users.data.remote

import com.example.users.data.JsonUsersList
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersService {

    @GET("api/")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("results") results: Int = 25,
        @Query("seed") seed: String = "SwapCardTest",
        @Query("inc") inc: String = "name,picture"
    ): JsonUsersList
}