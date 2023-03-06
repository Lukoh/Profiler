package com.goforer.profiler.presentation.ui.screen.navigation.destination

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.sharp.Notifications
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.goforer.profiler.presentation.stateholder.business.notification.NotificationViewModel
import com.goforer.profiler.presentation.ui.screen.compose.notification.content.ContentScreen
import com.goforer.profiler.presentation.ui.screen.compose.notification.notifications.NotificationScreen
import com.goforer.profiler.presentation.ui.screen.navigation.destination.ProfilerDestination.Companion.contentRoute
import com.goforer.profiler.presentation.ui.screen.navigation.destination.ProfilerDestination.Companion.notificationsStartRoute
import com.goforer.profiler.presentation.ui.screen.navigation.ext.navigateSingleTopTo

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
        navController.previousBackStackEntry?.let {
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