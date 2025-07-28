package com.example.user_detail.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation

const val USER_Details_GRAPH_ROUTE = "user_details_graph"


fun NavGraphBuilder.userDetailsGraph() {
    navigation(
        startDestination = USER_DETAILS_ROUTE,
        route = USER_Details_GRAPH_ROUTE,
    ) {
        userDetailsScreen()
    }
}