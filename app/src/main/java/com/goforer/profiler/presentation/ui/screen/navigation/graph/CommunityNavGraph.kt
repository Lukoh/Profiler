package com.goforer.profiler.presentation.ui.screen.navigation.graph

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.goforer.profiler.presentation.ui.screen.navigation.destination.Community
import com.goforer.profiler.presentation.ui.screen.navigation.destination.ProfilerDestination.Companion.communitiesStartRoute
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.communityGraph(
    navController: NavHostController,
    startDestination: String,
    route: String
) {
    navigation(startDestination = startDestination, route = route) {
        composable(route = communitiesStartRoute) {
            Community.screen(navController, it.arguments)
        }
    }
}