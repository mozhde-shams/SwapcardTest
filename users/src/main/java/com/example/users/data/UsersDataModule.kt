package com.example.users.data

import com.example.users.domain.UsersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class UsersDataModule {

    @Binds
    abstract fun bindUsersRepository(
        usersRepositoryImpl: UsersRepositoryImpl
    ): UsersRepository
}