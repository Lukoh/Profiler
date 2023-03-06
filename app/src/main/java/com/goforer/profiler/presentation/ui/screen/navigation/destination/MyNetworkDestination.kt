package com.goforer.profiler.presentation.ui.screen.navigation.destination

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.sharp.Person
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.goforer.profiler.presentation.stateholder.business.mynetwork.MyNetworkViewModel
import com.goforer.profiler.presentation.ui.screen.compose.home.mynetwork.detail.DetailScreen
import com.goforer.profiler.presentation.ui.screen.compose.home.mynetwork.networks.MyNetworkScreen
import com.goforer.profiler.presentation.ui.screen.navigation.destination.ProfilerDestination.Companion.detailInfoRoute
import com.goforer.profiler.presentation.ui.screen.navigation.destination.ProfilerDestination.Companion.myNetworksStartRoute
import com.goforer.profiler.presentation.ui.screen.navigation.ext.navigateSingleTopTo

object MyNetworks : ProfilerDestination {
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