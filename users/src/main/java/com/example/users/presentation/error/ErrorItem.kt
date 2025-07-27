package com.example.users.presentation.error

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.users.R
import com.example.users.presentation.UsersEvent

@Composable
internal fun ErrorItem(
    dispatch: (UsersEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            modifier = Modifier.padding(top = 8.dp),
            onClick = {
                dispatch(UsersEvent.ErrorItemRetryClicked)
            },
        ) {
            Text(text = stringResource(id = R.string.try_again))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorItemPreview() {
    ErrorItem(
        dispatch = {}
    )
}