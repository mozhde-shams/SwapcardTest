package com.example.users.presentation.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.users.domain.User
import com.example.users.presentation.UsersScreen
import com.example.users.presentation.UsersViewModel

const val USERS_ROUTE = "users"

internal fun NavGraphBuilder.usersScreen(
    onUserDetailsRequested: (User) -> Unit
) {
    composable(route = USERS_ROUTE) {
        val viewModel: UsersViewModel = hiltViewModel()
        val state by viewModel.state.collectAsState()
        UsersScreen(
            state = state,
            dispatch = viewModel::dispatch,
            userDetailsRequested = onUserDetailsRequested,
        )
    }
}