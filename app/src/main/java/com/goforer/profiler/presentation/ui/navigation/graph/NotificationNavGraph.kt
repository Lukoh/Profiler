/*
 * Copyright (C) 2023 The Android Open Source Project by Lukoh Nam, goForer
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License,
 * or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 */

package com.goforer.profiler.presentation.ui.navigation.graph

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Stable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.goforer.profiler.presentation.ui.navigation.destination.Content
import com.goforer.profiler.presentation.ui.navigation.destination.Content.routeWithArgs
import com.goforer.profiler.presentation.ui.navigation.destination.Notifications
import com.goforer.profiler.presentation.ui.navigation.destination.ProfilerDestination.Companion.notificationsStartRoute
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Stable
fun NavGraphBuilder.notificationGraph(
    navController: NavHostController,
    startDestination: String,
    route: String
) {
    navigation(startDestination = startDestination, route = route) {
        composable(route = notificationsStartRoute) {
            Notifications.screen(navController, it.arguments)
        }

        composable(route = routeWithArgs, arguments = Content.arguments) {
            Content.screen(navController, it.arguments)
        }
    }
}