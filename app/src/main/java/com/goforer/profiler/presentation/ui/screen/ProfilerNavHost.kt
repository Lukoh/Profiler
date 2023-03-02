package com.goforer.profiler.presentation.ui.screen

import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.goforer.profiler.presentation.ui.MainActivity.Companion.navigationRoutes
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ProfilerNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberAnimatedNavController()
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = navigationRoutes[0],
        modifier = modifier
    ) {
        profileGraph(navController, navigationRoute = navigationRoutes[0], startDestination = Profiles.route)
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }

        launchSingleTop = true
        restoreState = true
    }