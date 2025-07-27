package com.example.users.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.users.data.JsonUser

class UsersPagingSource(
    private val remote: Remote,
) : PagingSource<Int, JsonUser>() {
    override fun getRefreshKey(state: PagingState<Int, JsonUser>): Int? {
        return state.anchorPosition?.let { anchor ->
            val closestPage = state.closestPageToPosition(anchor)
            closestPage?.prevKey?.plus(1) ?: closestPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, JsonUser> {
        return try {
            val currentPage = params.key ?: 1
            val users = remote.getUsersList(currentPage)
            LoadResult.Page(
                data = users.usersList,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = currentPage + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}