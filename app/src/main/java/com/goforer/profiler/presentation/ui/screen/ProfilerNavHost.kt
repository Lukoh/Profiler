package com.goforer.profiler.presentation.ui.screen

import androidx.compose.animation.*
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
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
        val effect = tween<IntOffset>(durationMillis = 700, easing = CubicBezierEasing(0.08f,0.93f,0.68f,1.27f))

        composable(route = Profiles.route,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { it }, animationSpec = effect)
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { -(it / 2) }, animationSpec = effect)
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { -(it / 2) }, animationSpec = effect)
            },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = { it }, animationSpec = effect)
            }
        ) {
            Profiles.screen(navController, it.arguments)
        }

        composable(
            DetailInfo.routeWithArgs,
            arguments = DetailInfo.arguments,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { it }, animationSpec = effect)
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { -(it / 2) }, animationSpec = effect)
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { -(it / 2) }, animationSpec = effect)
            },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = { it }, animationSpec = effect)
            }
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