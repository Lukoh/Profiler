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

package com.goforer.profiler.presentation.ui.navigation.destination

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.sharp.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.goforer.profiler.presentation.stateholder.business.notification.NotificationViewModel
import com.goforer.profiler.presentation.stateholder.ui.notification.content.rememberContentContentState
import com.goforer.profiler.presentation.stateholder.ui.notification.notifications.rememberNotificationContentState
import com.goforer.profiler.presentation.stateholder.ui.rememberBaseUiState
import com.goforer.profiler.presentation.ui.screen.home.notification.content.ContentScreen
import com.goforer.profiler.presentation.ui.screen.home.notification.notifications.NotificationScreen
import com.goforer.profiler.presentation.ui.navigation.destination.ProfilerDestination.Companion.contentRoute
import com.goforer.profiler.presentation.ui.navigation.destination.ProfilerDestination.Companion.notificationsStartRoute
import com.goforer.profiler.presentation.ui.navigation.ext.navigateSingleTopTo

object Notifications : ProfilerDestination {
    override val icon = Icons.Sharp.Notifications
    override val route = notificationsStartRoute
    @OptIn(ExperimentalComposeUiApi::class)
    @RequiresApi(Build.VERSION_CODES.N)
    override val screen: @Composable (navController: NavHostController, arguments: Bundle?) -> Unit = { navController, _ ->
        navController.currentBackStackEntry?.let {
            val viewModel: NotificationViewModel =  hiltViewModel(it)

            NotificationScreen(
                state = rememberNotificationContentState(baseUiState = rememberBaseUiState(resourceStateFlow = viewModel.uiStateFlow)),
                onNavigateToDetailInfo = { userId ->
                    navController.navigateSingleTopTo("${Content.route}/$userId")
                }
            )
        }
    }
}

object Content : ProfilerDestination {
    override val icon = Icons.Filled.Details
    override val route = contentRoute
    private const val idTypeArg = "user_id"
    val routeWithArgs = "$route/{$idTypeArg}"
    val arguments = listOf(
        navArgument(idTypeArg) { type = NavType.IntType }
    )

    @RequiresApi(Build.VERSION_CODES.N)
    override val screen: @Composable (navController: NavHostController, bundle: Bundle?) -> Unit = { navController, bundle ->
        navController.previousBackStackEntry?.let {
            val viewModel: NotificationViewModel =  hiltViewModel(it)
            val id = bundle?.getInt(idTypeArg)

            id?.let { userId ->
                ContentScreen(
                    state  = rememberContentContentState(
                        uiStateFlow = viewModel.uiStateFlow,
                        onGetNotification =  viewModel::getNotification
                    ),
                    userId = userId,
                    onBackPressed = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}