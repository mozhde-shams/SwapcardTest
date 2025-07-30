package com.example.user_detail.presentation.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.user_detail.presentation.UserDetailsScreen
import com.example.user_detail.presentation.UserDetailsViewModel

const val ARG_USER_KEY = "userKey"
const val ARG_USER_NAME = "userName"
const val USER_DETAILS_ROUTE = "user_details/{$ARG_USER_KEY}/{$ARG_USER_NAME}"

internal fun NavGraphBuilder.userDetailsScreen() {
    composable(
        route = USER_DETAILS_ROUTE,
        arguments = listOf(
            navArgument(ARG_USER_KEY) { type = NavType.StringType },
            navArgument(ARG_USER_NAME) { type = NavType.StringType })
    ) {
        val viewModel: UserDetailsViewModel = hiltViewModel()
        val state by viewModel.state.collectAsState()
        UserDetailsScreen(
            dispatch = viewModel::dispatch,
            state = state,
        )
    }
}