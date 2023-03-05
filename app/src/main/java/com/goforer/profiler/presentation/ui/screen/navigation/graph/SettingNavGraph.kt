package com.goforer.profiler.presentation.ui.screen.navigation.graph

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.goforer.profiler.presentation.ui.screen.navigation.destination.ProfilerDestination.Companion.settingStartRoute
import com.goforer.profiler.presentation.ui.screen.navigation.destination.Setting
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.settingGraph(
    navController: NavHostController,
    startDestination: String,
    route: String
) {
    navigation(startDestination = startDestination, route = route) {
        composable(route = settingStartRoute) {
            Setting.screen(navController, it.arguments)
        }
    }
}