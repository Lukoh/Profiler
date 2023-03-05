package com.goforer.profiler.presentation.ui.screen

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.sharp.Commute
import androidx.compose.material.icons.sharp.Notifications
import androidx.compose.material.icons.sharp.Person
import androidx.compose.material.icons.sharp.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.goforer.profiler.presentation.stateholder.business.mynetwork.MyNetworkViewModel
import com.goforer.profiler.presentation.stateholder.business.notification.NotificationViewModel
import com.goforer.profiler.presentation.ui.ext.navigateSingleTopTo
import com.goforer.profiler.presentation.ui.screen.community.CommunityScreen
import com.goforer.profiler.presentation.ui.screen.mynetwork.detail.DetailScreen
import com.goforer.profiler.presentation.ui.screen.mynetwork.networks.MyNetworkScreen
import com.goforer.profiler.presentation.ui.screen.navgraph.CommunityNav.communitiesStartRoute
import com.goforer.profiler.presentation.ui.screen.navgraph.MyNetworkNav.detailInfoRoute
import com.goforer.profiler.presentation.ui.screen.navgraph.MyNetworkNav.myNetworksStartRoute
import com.goforer.profiler.presentation.ui.screen.navgraph.NotificationNav.contentRoute
import com.goforer.profiler.presentation.ui.screen.navgraph.NotificationNav.notificationsStartRoute
import com.goforer.profiler.presentation.ui.screen.navgraph.SettingNav.settingStartRoute
import com.goforer.profiler.presentation.ui.screen.notification.content.ContentScreen
import com.goforer.profiler.presentation.ui.screen.notification.notifications.NotificationScreen
import com.goforer.profiler.presentation.ui.screen.setting.SettingScreen

interface ProfilerDestination {
    val icon: ImageVector
    val route: String
    val screen: @Composable (navController: NavHostController, arguments: Bundle) -> Unit
}

//Profile
object Profiles : ProfilerDestination {
    override val icon = Icons.Sharp.Person
    override val route = myNetworksStartRoute
    override val screen: @Composable (navController: NavHostController, bundle: Bundle?) -> Unit = { navController, _ ->
        MyNetworkScreen(
            onNavigateToDetailInfo = {
                navController.navigateSingleTopTo("${DetailInfo.route}/$it")
            }
        )
    }
}

object DetailInfo : ProfilerDestination {
    override val route = detailInfoRoute
    private const val idTypeArg = "user_id"
    val routeWithArgs = "${route}/{${idTypeArg}}"
    val arguments = listOf(
        navArgument(idTypeArg) { type = NavType.IntType }
    )

    override val icon = Icons.Filled.Details
    @RequiresApi(Build.VERSION_CODES.N)
    override val screen: @Composable (navController: NavHostController, bundle: Bundle?) -> Unit = { navController, bundle ->
        navController.previousBackStackEntry?.let {
            val profileViewModel: MyNetworkViewModel =  hiltViewModel(it)
            val id = bundle?.getInt(idTypeArg)

            id?.let { userId ->
                DetailScreen(profileViewModel = profileViewModel, userId = userId, onBackPressed = {
                    navController.navigateUp()
                })
            }
        }
    }
}

//Community
object Community : ProfilerDestination {
    override val route = communitiesStartRoute

    override val icon = Icons.Sharp.Commute
    @RequiresApi(Build.VERSION_CODES.N)
    override val screen: @Composable (navController: NavHostController, bundle: Bundle?) -> Unit = { _, _ ->
        CommunityScreen()
    }
}

//Notification
object Notifications : ProfilerDestination {
    override val route = notificationsStartRoute
    override val icon = Icons.Sharp.Notifications
    @RequiresApi(Build.VERSION_CODES.N)
    override val screen: @Composable (navController: NavHostController, arguments: Bundle?) -> Unit = { navController, _ ->
        NotificationScreen(
            onNavigateToDetailInfo = {
                navController.navigateSingleTopTo("${Content.route}/$it")
            }
        )
    }
}

object Content : ProfilerDestination {
    override val route = contentRoute
    private const val idTypeArg = "user_id"
    val routeWithArgs = "${route}/{${idTypeArg}}"
    val arguments = listOf(
        navArgument(idTypeArg) { type = NavType.IntType }
    )

    override val icon = Icons.Filled.Details
    @RequiresApi(Build.VERSION_CODES.N)
    override val screen: @Composable (navController: NavHostController, bundle: Bundle?) -> Unit = { navController, bundle ->
        navController.currentBackStackEntry?.let {
            val notificationViewModel: NotificationViewModel =  hiltViewModel(it)
            val id = bundle?.getInt(idTypeArg)

            id?.let { userId ->
                ContentScreen(notificationViewModel = notificationViewModel, userId = userId, onBackPressed = {
                    navController.navigateUp()
                })
            }
        }
    }
}

//Setting
object Setting : ProfilerDestination {
    override val route = settingStartRoute

    override val icon = Icons.Sharp.Settings
    @RequiresApi(Build.VERSION_CODES.N)
    override val screen: @Composable (navController: NavHostController, bundle: Bundle?) -> Unit = { _, _ ->
        SettingScreen()
    }
}

val ProfilerScreens = listOf(Profiles, DetailInfo, Notifications, Content,  Community, Setting)