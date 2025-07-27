package com.example.users.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation

const val USERS_GRAPH_ROUTE = "users_graph"


fun NavGraphBuilder.usersGraph() {
    navigation(
        startDestination = USERS_ROUTE,
        route = USERS_GRAPH_ROUTE,
    ) {
        usersScreen()
    }
}