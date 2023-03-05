package com.goforer.profiler.presentation.ui.screen.navigation.destination

import android.os.Bundle
import androidx.compose.runtime.Composable
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

        internal const val notificationHomeRoute = "home/notificationHome"
        internal const val notificationsStartRoute = "home/notificationHome/notifications"
        internal const val contentRoute = "home/notificationHome/content"

        internal const val settingHomeRoute = "home/settingHome"
        internal const val settingStartRoute = "home/settingHome/setting"
    }
}