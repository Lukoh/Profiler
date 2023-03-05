package com.goforer.profiler.presentation.ui.screen.navgraph

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.goforer.profiler.presentation.ui.screen.Community
import com.goforer.profiler.presentation.ui.screen.navgraph.CommunityNav.communitiesStartRoute
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

object CommunityNav {
    internal const val communityHomeRoute = "home/communityHome"
    internal const val communitiesStartRoute = "home/communityHome/communities"
}