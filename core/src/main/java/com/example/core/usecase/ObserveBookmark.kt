package com.example.core.usecase

import com.example.core.db.BookmarkDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveBookmarkUseCase @Inject constructor(
    private val dao: BookmarkDao
) {
    operator fun invoke(key: String): Flow<Boolean> =
        dao.isBookmarked(key)
}