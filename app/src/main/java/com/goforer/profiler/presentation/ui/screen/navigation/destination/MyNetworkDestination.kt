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

package com.goforer.profiler.presentation.ui.screen.navigation.destination

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.sharp.People
import androidx.compose.material.icons.sharp.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.goforer.profiler.presentation.stateholder.business.mynetwork.MembersViewModel
import com.goforer.profiler.presentation.stateholder.business.mynetwork.MyNetworkViewModel
import com.goforer.profiler.presentation.stateholder.ui.mynetwork.detail.rememberDetailContentState
import com.goforer.profiler.presentation.stateholder.ui.mynetwork.members.rememberMembersContentState
import com.goforer.profiler.presentation.stateholder.ui.mynetwork.networks.rememberMyNetworkContentState
import com.goforer.profiler.presentation.ui.screen.compose.home.mynetwork.detail.DetailScreen
import com.goforer.profiler.presentation.ui.screen.compose.home.mynetwork.members.MembersScreen
import com.goforer.profiler.presentation.ui.screen.compose.home.mynetwork.networks.MyNetworkScreen
import com.goforer.profiler.presentation.ui.screen.navigation.destination.ProfilerDestination.Companion.detailInfoRoute
import com.goforer.profiler.presentation.ui.screen.navigation.destination.ProfilerDestination.Companion.membersRoute
import com.goforer.profiler.presentation.ui.screen.navigation.destination.ProfilerDestination.Companion.myNetworksStartRoute
import com.goforer.profiler.presentation.ui.screen.navigation.ext.navigateSingleTopTo

object MyNetworks : ProfilerDestination {
    override val icon = Icons.Sharp.Person
    override val route = myNetworksStartRoute
    @OptIn(ExperimentalComposeUiApi::class)
    override val screen: @Composable (navController: NavHostController, bundle: Bundle?) -> Unit = { navController, _ ->
        navController.currentBackStackEntry?.let {
            val viewModel: MyNetworkViewModel =  hiltViewModel(it)

            MyNetworkScreen(
                state = rememberMyNetworkContentState(
                    uiState = viewModel.uiState,
                    onFollowStatusChanged = viewModel::changeFollowStatus,
                    onGetMember = viewModel::getMember,
                    onEstimated = viewModel::setEstimation,
                    onDeleteMember = viewModel::deleteMember
                ),
                onNavigateToDetailInfo = { userId ->
                    navController.navigateSingleTopTo("${DetailInfo.route}/$userId")
                }
            )
        }
    }
}

object DetailInfo : ProfilerDestination {
    override val icon = Icons.Filled.Details
    override val route = detailInfoRoute
    private const val idTypeArg = "user_id"
    val routeWithArgs = "${route}/{${idTypeArg}}"
    val arguments = listOf(
        navArgument(idTypeArg) { type = NavType.IntType }
    )

    @Stable
    @RequiresApi(Build.VERSION_CODES.N)
    override val screen: @Composable (navController: NavHostController, bundle: Bundle?) -> Unit = { navController, bundle ->
        navController.previousBackStackEntry?.let {
            val viewModel: MyNetworkViewModel =  hiltViewModel(it)
            val id = bundle?.getInt(idTypeArg)

            id?.let { userId ->
                DetailScreen(
                    state = rememberDetailContentState(
                        uiState = viewModel.uiState,
                        onGetMember =  viewModel::getMember
                    ),
                    userId = userId,
                    onMembersClicked = {
                        navController.navigateSingleTopTo(Members.route)
                    },
                    onBackPressed = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

object Members : ProfilerDestination {
    override val icon = Icons.Sharp.People
    override val route = membersRoute
    override val screen: @Composable (navController: NavHostController, bundle: Bundle?) -> Unit = { navController, _ ->
        navController.previousBackStackEntry?.let {
            val viewModel: MembersViewModel =  hiltViewModel(it)

            MembersScreen(
                state = rememberMembersContentState(
                    uiState = viewModel.uiState,
                    onGetMembers = viewModel::getMembers,
                    onGetMember = viewModel::getMember,
                    onEstimated = viewModel::setEstimation
                ),
                onBackPressed = {
                    navController.navigateUp()
                }
            )
        }
    }
}