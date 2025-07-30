package com.example.core.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): UsersDatabase =
        Room.databaseBuilder(
            context,
            UsersDatabase::class.java,
            "user.db"
        )
            .fallbackToDestructiveMigration(false)
            .build()

    @Provides
    fun provideBookmarkDao(db: UsersDatabase): BookmarkDao =
        db.bookmarkDao()
}