package com.goforer.profiler.presentation.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.google.accompanist.navigation.animation.composable
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
        startDestination = Profiles.route,
        modifier = modifier
    ) {
        composable(route = Profiles.route,
            enterTransition = { slideInHorizontally(animationSpec = tween(500)) },
            exitTransition = { slideOutHorizontally(animationSpec = tween(500)) }
        ) {
            Profiles.screen(navController, it.arguments)
        }

        composable(
            DetailInfo.routeWithArgs,
            enterTransition = { slideInHorizontally(initialOffsetX = { it / 2 }, animationSpec = tween(500)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { it / 2 }, animationSpec = tween(500)) }
        ) {
           DetailInfo.screen(navController, it.arguments)
        }
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