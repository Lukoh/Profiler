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

package com.goforer.profiler.presentation.ui.screen.navigation.destination

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.navigation.NavHostController
import com.goforer.profiler.presentation.ui.screen.compose.home.setting.SettingScreen
import com.goforer.profiler.presentation.ui.screen.navigation.destination.ProfilerDestination.Companion.settingStartRoute

@Stable
object Setting : ProfilerDestination {
    override val icon = Icons.Sharp.Settings
    override val route = settingStartRoute
    @RequiresApi(Build.VERSION_CODES.N)
    override val screen: @Composable (navController: NavHostController, bundle: Bundle?) -> Unit = { _, _ ->
        SettingScreen()
    }
}