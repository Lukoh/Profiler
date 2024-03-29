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

package com.goforer.profiler.presentation.ui.screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import com.goforer.profiler.presentation.ui.screen.home.ProfilerHomeScreen
import com.goforer.profiler.presentation.ui.screen.home.ProfilerScreens
import com.goforer.profiler.presentation.ui.screen.landing.LandingScreen
import com.goforer.profiler.presentation.ui.navigation.destination.MyNetworks
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(windowSizeClass: WindowSizeClass) {
    Surface(color = MaterialTheme.colorScheme.primary) {
        var showLandingScreen by remember { mutableStateOf(true) }

        if (showLandingScreen) {
            LandingScreen(onTimeout = { showLandingScreen = false })
        } else {
            val navController = rememberAnimatedNavController()
            val currentBackStack by navController.currentBackStackEntryAsState()
            val currentDestination = currentBackStack?.destination
            val currentScreen = ProfilerScreens.find { it.route == currentDestination?.route } ?: MyNetworks

            ProfilerHomeScreen(
                windowSizeClass = windowSizeClass,
                modifier = Modifier,
                navController = navController
            )
        }
    }
}