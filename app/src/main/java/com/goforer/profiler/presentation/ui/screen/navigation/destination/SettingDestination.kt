package com.goforer.profiler.presentation.ui.screen.navigation.destination

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Settings
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.goforer.profiler.presentation.ui.screen.compose.setting.SettingScreen
import com.goforer.profiler.presentation.ui.screen.navigation.destination.ProfilerDestination.Companion.settingStartRoute

object Setting : ProfilerDestination {
    override val route = settingStartRoute

    override val icon = Icons.Sharp.Settings
    @RequiresApi(Build.VERSION_CODES.N)
    override val screen: @Composable (navController: NavHostController, bundle: Bundle?) -> Unit = { _, _ ->
        SettingScreen()
    }
}