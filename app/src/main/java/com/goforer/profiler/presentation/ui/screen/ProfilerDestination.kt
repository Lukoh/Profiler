package com.goforer.profiler.presentation.ui.screen

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adb
import androidx.compose.material.icons.filled.Details
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.goforer.profiler.presentation.stateholder.business.profile.ProfileViewModel
import com.goforer.profiler.presentation.ui.MainActivity.Companion.DetailInfoRoute
import com.goforer.profiler.presentation.ui.MainActivity.Companion.ProfilesRoute
import com.goforer.profiler.presentation.ui.screen.profile.ProfileScreen
import com.goforer.profiler.presentation.ui.screen.detail.DetailScreen

interface ProfilerDestination {
    val icon: ImageVector
    val route: String
    val screen: @Composable (navController: NavHostController, arguments: Bundle) -> Unit
}

object Profiles : ProfilerDestination {
    override val icon = Icons.Filled.Adb
    override val route = ProfilesRoute
    override val screen: @Composable (navController: NavHostController, arguments: Bundle?) -> Unit = { navHostController, _ ->
        ProfileScreen(
            onNavigateToDetailInfo = {
                navHostController.navigateSingleTopTo("${DetailInfo.route}/$it")
            }
        )
    }
}

object DetailInfo : ProfilerDestination {
    override val route = DetailInfoRoute
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

val ProfilerScreens = listOf(Profiles, DetailInfo)