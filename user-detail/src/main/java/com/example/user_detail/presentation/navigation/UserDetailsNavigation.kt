package com.example.user_detail.presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.user_detail.presentation.UserDetailsScreen
import com.example.user_detail.presentation.UserDetailsViewModel

const val ARG_NAME = "userName"
const val USER_DETAILS_ROUTE = "user_details/{$ARG_NAME}"

internal fun NavGraphBuilder.userDetailsScreen() {
    composable(
        route = USER_DETAILS_ROUTE,
        arguments = listOf(
            navArgument(ARG_NAME) { type = NavType.StringType },
        )
    ) {
        val viewModel: UserDetailsViewModel = hiltViewModel()
        UserDetailsScreen(viewModel)
    }
}