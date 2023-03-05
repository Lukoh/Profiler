package com.goforer.profiler.presentation.ui.screen.navigation.destination

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Commute
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.goforer.profiler.presentation.ui.screen.content.community.CommunityScreen
import com.goforer.profiler.presentation.ui.screen.navigation.destination.ProfilerDestination.Companion.communitiesStartRoute

object Community : ProfilerDestination {
    override val route = communitiesStartRoute

    override val icon = Icons.Sharp.Commute
    @RequiresApi(Build.VERSION_CODES.N)
    override val screen: @Composable (navController: NavHostController, bundle: Bundle?) -> Unit = { _, _ ->
        CommunityScreen()
    }
}