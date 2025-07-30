package com.example.core.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BookmarkEntity::class], version = 1)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao
}
