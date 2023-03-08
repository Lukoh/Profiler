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

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController

interface ProfilerDestination {
    val icon: ImageVector
    val route: String
    val screen: @Composable (navController: NavHostController, arguments: Bundle) -> Unit

    companion object {
        internal const val communityHomeRoute = "home/communityHome"
        internal const val communitiesStartRoute = "home/communityHome/communities"

        internal const val myNetworkHomeRoute = "home/profileHome"
        internal const val myNetworksStartRoute = "home/profileHome/myNetworks"
        internal const val detailInfoRoute = "home/profileHome/detailInfo"
        internal const val membersRoute = "home/profileHome/members"

        internal const val notificationHomeRoute = "home/notificationHome"
        internal const val notificationsStartRoute = "home/notificationHome/notifications"
        internal const val contentRoute = "home/notificationHome/content"

        internal const val settingHomeRoute = "home/settingHome"
        internal const val settingStartRoute = "home/settingHome/setting"
    }
}