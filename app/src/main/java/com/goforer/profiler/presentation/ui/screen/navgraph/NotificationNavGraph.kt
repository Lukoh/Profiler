package com.goforer.profiler.presentation.ui.screen.navgraph

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.goforer.profiler.presentation.ui.screen.Content
import com.goforer.profiler.presentation.ui.screen.Content.routeWithArgs
import com.goforer.profiler.presentation.ui.screen.Notifications
import com.goforer.profiler.presentation.ui.screen.navgraph.NotificationNav.notificationsStartRoute
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

object NotificationNav {
    internal const val notificationHomeRoute = "home/notificationHome"
    internal const val notificationsStartRoute = "home/notificationHome/notifications"
    internal const val contentRoute = "home/notificationHome/content"
}