package com.goforer.profiler.presentation.ui.screen.navgraph

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.goforer.profiler.presentation.ui.screen.DetailInfo
import com.goforer.profiler.presentation.ui.screen.DetailInfo.routeWithArgs
import com.goforer.profiler.presentation.ui.screen.Profiles
import com.goforer.profiler.presentation.ui.screen.navgraph.MyNetworkNav.myNetworksStartRoute
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.networkGraph(
    navController: NavHostController,
    startDestination: String,
    route: String
) {
    navigation(startDestination = startDestination, route = route) {
        composable(route = myNetworksStartRoute) {
            Profiles.screen(navController, it.arguments)
        }

        composable(route = routeWithArgs, arguments = DetailInfo.arguments) {
            DetailInfo.screen(navController, it.arguments)
        }
    }
}

object MyNetworkNav {
    internal const val myNetworkHomeRoute = "home/profileHome"
    internal const val myNetworksStartRoute = "home/profileHome/myNetworks"
    internal const val detailInfoRoute = "home/profileHome/detailInfo"
}