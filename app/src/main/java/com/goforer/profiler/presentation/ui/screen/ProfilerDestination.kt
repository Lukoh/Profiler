package com.goforer.profiler.presentation.ui

import android.os.Bundle
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adb
import androidx.compose.material.icons.filled.Details
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
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

    override val icon = Icons.Filled.Details
    override val screen: @Composable (navController: NavHostController, arguments: Bundle?) -> Unit = { navController, arguments ->
        navController.previousBackStackEntry?.let {
            val profileViewModel: ProfileViewModel = viewModel(it)
            val id = arguments?.getInt(idTypeArg)

            id?.let { userId ->
                DetailScreen(profileViewModel = profileViewModel, userId = userId, onBackPressed = {
                    navController.popBackStack()
                })
            }
        }
    }
}

val ProfilerScreens = listOf(Profiles, DetailInfo)