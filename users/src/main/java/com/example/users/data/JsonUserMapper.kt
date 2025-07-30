package com.example.users.data

import com.example.core.db.BookmarkDao
import com.example.users.domain.User

internal fun JsonUser.toDomain(
    bookmarkDao: BookmarkDao
): User {
    val sha = sha256KeyOf(this)
    val isBookmarkedFlow = bookmarkDao.isBookmarked(sha)
    return User(
        firstName = name.firstName,
        lastName = name.lastName,
        picture = picture.medium,
        id = id.value,
        isBookmarked = isBookmarkedFlow,
    )
}