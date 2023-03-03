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
import com.goforer.profiler.presentation.stateholder.business.profile.ProfileViewModel
import com.goforer.profiler.presentation.ui.MainActivity.Companion.communityRoutes
import com.goforer.profiler.presentation.ui.MainActivity.Companion.notificationRoutes
import com.goforer.profiler.presentation.ui.MainActivity.Companion.profileRoutes
import com.goforer.profiler.presentation.ui.MainActivity.Companion.settingRoutes
import com.goforer.profiler.presentation.ui.screen.community.CommunityScreen
import com.goforer.profiler.presentation.ui.screen.notification.NotificationScreen
import com.goforer.profiler.presentation.ui.screen.profile.profiles.ProfileScreen
import com.goforer.profiler.presentation.ui.screen.profile.detail.DetailScreen
import com.goforer.profiler.presentation.ui.screen.setting.SettingScreen

interface ProfilerDestination {
    val icon: ImageVector
    val route: String
    val screen: @Composable (navController: NavHostController, arguments: Bundle) -> Unit
}

//Profile
object Profiles : ProfilerDestination {
    override val icon = Icons.Sharp.Person
    override val route = profileRoutes[0]
    override val screen: @Composable (navController: NavHostController, arguments: Bundle?) -> Unit = { navHostController, _ ->
        ProfileScreen(
            onNavigateToDetailInfo = {
                navHostController.navigateSingleTopTo("${DetailInfo.route}/$it")
            }
        )
    }
}

object DetailInfo : ProfilerDestination {
    override val route = profileRoutes[0]
    private const val idTypeArg = "user_id"
    val routeWithArgs = "${route}/{${idTypeArg}}"
    val arguments = listOf(
        navArgument(idTypeArg) { type = NavType.IntType }
    )

    override val icon = Icons.Filled.Details
    @RequiresApi(Build.VERSION_CODES.N)
    override val screen: @Composable (navController: NavHostController, arguments: Bundle?) -> Unit = { navController, arguments ->
        navController.previousBackStackEntry?.let {
            val profileViewModel: ProfileViewModel =  hiltViewModel(it)
            val id = arguments?.getInt(idTypeArg)

            id?.let { userId ->
                DetailScreen(profileViewModel = profileViewModel, userId = userId, onBackPressed = {
                    if (navController.previousBackStackEntry?.destination?.route == Profiles.route)
                        navController.navigateUp()
                })
            }
        }
    }
}

//Community
object Community : ProfilerDestination {
    override val route = communityRoutes[0]

    override val icon = Icons.Sharp.Commute
    @RequiresApi(Build.VERSION_CODES.N)
    override val screen: @Composable (navController: NavHostController, arguments: Bundle?) -> Unit = { navController, _ ->
        navController.previousBackStackEntry?.let {
            CommunityScreen()
        }
    }
}

//Notification
object Notification : ProfilerDestination {
    override val route = notificationRoutes[0]

    override val icon = Icons.Sharp.Notifications
    @RequiresApi(Build.VERSION_CODES.N)
    override val screen: @Composable (navController: NavHostController, arguments: Bundle?) -> Unit = { navController, _ ->
        navController.previousBackStackEntry?.let {
            NotificationScreen()
        }
    }
}

//Setting
object Setting : ProfilerDestination {
    override val route = settingRoutes[0]

    override val icon = Icons.Sharp.Settings
    @RequiresApi(Build.VERSION_CODES.N)
    override val screen: @Composable (navController: NavHostController, arguments: Bundle?) -> Unit = { navController, _ ->
        navController.previousBackStackEntry?.let {
            SettingScreen()
        }
    }
}

val ProfilerScreens = listOf(Profiles, DetailInfo, Community, Setting)