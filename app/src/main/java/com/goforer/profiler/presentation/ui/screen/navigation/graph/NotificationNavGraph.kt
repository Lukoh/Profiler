package com.goforer.profiler.presentation.ui.screen.navigation.graph

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.goforer.profiler.presentation.ui.screen.navigation.destination.Content
import com.goforer.profiler.presentation.ui.screen.navigation.destination.Content.routeWithArgs
import com.goforer.profiler.presentation.ui.screen.navigation.destination.Notifications
import com.goforer.profiler.presentation.ui.screen.navigation.destination.ProfilerDestination.Companion.notificationsStartRoute
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.notificationGraph(
    navController: NavHostController,
    startDestination: String,
    route: String
) {
    navigation(startDestination = startDestination, route = route) {
        composable(route = notificationsStartRoute) {
            Notifications.screen(navController, it.arguments)
        }

        composable(route = routeWithArgs, arguments = Content.arguments) {
            Content.screen(navController, it.arguments)
        }
    }
}