package com.goforer.profiler.presentation.ui.screen.navgraph

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.goforer.profiler.presentation.ui.screen.Setting
import com.goforer.profiler.presentation.ui.screen.navgraph.SettingNav.settingStartRoute
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

object SettingNav {
    internal const val settingHomeRoute = "home/settingHome"
    internal const val settingStartRoute = "home/settingHome/setting"
}