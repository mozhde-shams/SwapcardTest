package com.example.users.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import com.example.users.R
import com.example.users.data.sha256KeyOf
import com.example.users.domain.User
import com.example.users.presentation.error.ErrorItem
import com.example.users.presentation.error.ErrorScreen
import com.example.users.presentation.loading.LoadingItem
import com.example.users.presentation.loading.LoadingScreen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun UsersScreen(
    state: UsersState,
    dispatch: (UsersEvent) -> Unit,
    userDetailsRequested: (user: User) -> Unit,
) {
    val lazyPagingItems: LazyPagingItems<User> = state.users.collectAsLazyPagingItems()

    if (state.needsRetry) {
        lazyPagingItems.retry()
        dispatch(UsersEvent.ResetRetry)
    }

    if (state.needsRefresh) {
        lazyPagingItems.refresh()
        dispatch(UsersEvent.ResetRefresh)
    }

    when {
        lazyPagingItems.loadState.refresh is LoadState.Loading && lazyPagingItems.itemCount == 0 ->
            LoadingScreen()

        lazyPagingItems.loadState.refresh is LoadState.Error && lazyPagingItems.itemCount == 0 ->
            ErrorScreen(dispatch)

        else -> {
            PullToRefreshBox(
                isRefreshing = state.needsRefresh,
                onRefresh = {
                    dispatch(UsersEvent.PullToRefresh)
                }
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(all = 4.dp),
                ) {
                    items(lazyPagingItems.itemCount) { index ->
                        lazyPagingItems[index]?.let { user ->
                            UserRow(
                                user = user,
                                onClick = userDetailsRequested,
                                dispatch = dispatch,
                            )
                        }
                    }
                    lazyPagingItems.apply {
                        when {
                            loadState.refresh is LoadState.Loading -> {
                                item { LoadingItem() }
                            }

                            loadState.append is LoadState.Loading -> {
                                item { LoadingItem() }
                            }

                            loadState.refresh is LoadState.Error -> {
                                item { ErrorItem(dispatch) }
                            }

                            loadState.append is LoadState.Error -> {
                                item { ErrorItem(dispatch) }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun UserRow(
    user: User,
    onClick: (user: User) -> Unit,
    dispatch: (UsersEvent) -> Unit,
    modifier: Modifier = Modifier,
) {

    val isBookmarked by user.isBookmarked.collectAsState(initial = false)
    val shaKey = sha256KeyOf(user)
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onClick(user)
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = user.picture,
                    contentDescription = "${user.firstName.orEmpty()} ${user.lastName.orEmpty()}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "${user.firstName.orEmpty()} ${user.lastName.orEmpty()}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }
            IconButton(
                onClick = {
                    dispatch(UsersEvent.ToggleBookmarkClicked(shaKey))
                }
            ) {
                val iconRes = if (isBookmarked)
                    R.drawable.bookmark_added
                else
                    R.drawable.bookmark_remove
                Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = if (isBookmarked)
                        stringResource(R.string.remove_bookmark)
                    else
                        stringResource(R.string.add_bookmark)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UsersScreenPreview() {
    val sampleUsers = listOf(
        User(
            firstName = "Emma",
            lastName = "Johnson",
            picture = "",
            id = "40639914",
            isBookmarked = flowOf(true),
        ),
        User(
            firstName = "Liam",
            lastName = "Carter",
            picture = "",
            id = "211929861",
            isBookmarked = flowOf(false),
        )
    )

    val pagingItemsFlow: Flow<PagingData<User>> = flow {
        emit(PagingData.from(sampleUsers))
    }

    UsersScreen(
        state = UsersState(
            needsRetry = false,
            users = pagingItemsFlow
        ),
        dispatch = {},
        userDetailsRequested = {},
    )
}