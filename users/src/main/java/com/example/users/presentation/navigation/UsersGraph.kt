package com.example.users.presentation.navigation

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.example.users.data.sha256KeyOf

const val USERS_GRAPH_ROUTE = "users_graph"


fun NavGraphBuilder.usersGraph(
    navController: NavController
) {
    navigation(
        startDestination = USERS_ROUTE,
        route = USERS_GRAPH_ROUTE,
    ) {
        usersScreen { user ->
            val shaKey = sha256KeyOf(user)
            val name = "${user.firstName.orEmpty()} ${user.lastName.orEmpty()}"
            val encodedKey = Uri.encode(shaKey)
            val encodedName = Uri.encode(name)
            navController.navigate(
                "user_details/$encodedKey/$encodedName"
            )
        }
    }
}