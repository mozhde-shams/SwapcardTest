package com.example.swapcardtest

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.users.presentation.navigation.USERS_GRAPH_ROUTE
import com.example.users.presentation.navigation.usersGraph

@Composable
fun AppHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = USERS_GRAPH_ROUTE,
    ) {
        usersGraph()
    }
}