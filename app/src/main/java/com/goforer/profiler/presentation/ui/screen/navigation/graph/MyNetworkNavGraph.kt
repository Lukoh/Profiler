package com.goforer.profiler.presentation.ui.screen.navigation.graph

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.goforer.profiler.presentation.ui.screen.navigation.destination.DetailInfo
import com.goforer.profiler.presentation.ui.screen.navigation.destination.DetailInfo.routeWithArgs
import com.goforer.profiler.presentation.ui.screen.navigation.destination.MyNetworks
import com.goforer.profiler.presentation.ui.screen.navigation.destination.ProfilerDestination.Companion.myNetworksStartRoute
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.networkGraph(
    navController: NavHostController,
    startDestination: String,
    route: String
) {
    navigation(startDestination = startDestination, route = route) {
        composable(route = myNetworksStartRoute) {
            MyNetworks.screen(navController, it.arguments)
        }

        composable(route = routeWithArgs, arguments = DetailInfo.arguments) {
            DetailInfo.screen(navController, it.arguments)
        }
    }
}