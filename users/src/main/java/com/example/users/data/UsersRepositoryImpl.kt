package com.example.users.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.core.db.BookmarkDao
import com.example.users.data.remote.Remote
import com.example.users.data.remote.UsersPagingSource
import com.example.users.domain.User
import com.example.users.domain.UsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val remote: Remote,
    private val bookmarkDao: BookmarkDao,
) : UsersRepository {

    companion object {
        const val PAGE_SIZE = 25
        const val PREFETCH_DISTANCE = 2
    }

    override suspend fun getUsersList(): Flow<PagingData<User>> = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            prefetchDistance = PREFETCH_DISTANCE,
        ),
        pagingSourceFactory = {
            UsersPagingSource(remote)
        }
    ).flow.map { usersPagingData ->
        usersPagingData.map { jsonUser ->
            jsonUser.toDomain(bookmarkDao)
        }
    }
}