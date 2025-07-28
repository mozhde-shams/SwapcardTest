package com.example.users.presentation.navigation

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation

const val USERS_GRAPH_ROUTE = "users_graph"


fun NavGraphBuilder.usersGraph(
    navController: NavController
) {
    navigation(
        startDestination = USERS_ROUTE,
        route = USERS_GRAPH_ROUTE,
    ) {
        usersScreen { userName ->
            navController.navigate(
                "user_details/${Uri.encode(userName)}"
            )
        }
    }
}