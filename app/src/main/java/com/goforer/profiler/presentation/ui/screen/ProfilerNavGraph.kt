package com.goforer.profiler.presentation.ui.screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.profileGraph(navController: NavHostController, navigationRoute: String, startDestination: String ) {
    navigation(startDestination = startDestination, route = navigationRoute) {
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