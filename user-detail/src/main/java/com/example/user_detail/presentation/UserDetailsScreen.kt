package com.example.user_detail.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.user_detail.R

@Composable
fun UserDetailsScreen(
    state: UserDetailsState,
    dispatch: (UserDetailsEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = state.userName,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Start,
            modifier = Modifier.weight(1f)
        )

        IconButton(
            onClick = {
                dispatch(UserDetailsEvent.ToggleBookmarkClicked(state.userKey))
            }
        ) {
            val isBookmarked = state.isBookmarked
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

@Preview(showBackground = true)
@Composable
fun PreviewUserDetailsScreen() {
    UserDetailsScreen(
        state = UserDetailsState(),
        dispatch = {},
    )
}

