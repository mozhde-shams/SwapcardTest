package com.example.core.usecase

import com.example.core.db.BookmarkDao
import com.example.core.db.BookmarkEntity
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class ToggleBookmarkUseCase @Inject constructor(
    private val bookmarkDao: BookmarkDao
) {
    suspend operator fun invoke(key: String) {
        val currently = bookmarkDao.isBookmarked(key).first()
        if (currently) {
            bookmarkDao.remove(BookmarkEntity(bookmarkKey = key))
        } else {
            bookmarkDao.add(BookmarkEntity(bookmarkKey = key))
        }
    }
}