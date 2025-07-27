package com.example.users.presentation.error

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.users.R
import com.example.users.presentation.UsersEvent

@Composable
internal fun ErrorScreen(
    dispatch: (UsersEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(R.drawable.something_went_wrong),
            contentDescription = stringResource(id = R.string.something_went_wrong)
        )
        Text(text = stringResource(id = R.string.something_went_wrong))
        Button(
            modifier = Modifier.padding(top = 8.dp),
            onClick = {
                dispatch(UsersEvent.ErrorScreenTryAgainClicked)
            },
        ) {
            Text(text = stringResource(id = R.string.try_again))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    ErrorScreen(
        dispatch = {}
    )
}