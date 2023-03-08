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

package com.goforer.profiler.presentation.ui.screen.compose.home

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.*
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.goforer.profiler.R
import com.goforer.profiler.presentation.ui.screen.navigation.*
import com.goforer.profiler.presentation.ui.screen.navigation.destination.*
import com.goforer.profiler.presentation.ui.screen.navigation.destination.ProfilerDestination.Companion.communitiesStartRoute
import com.goforer.profiler.presentation.ui.screen.navigation.destination.ProfilerDestination.Companion.communityHomeRoute
import com.goforer.profiler.presentation.ui.screen.navigation.destination.ProfilerDestination.Companion.myNetworkHomeRoute
import com.goforer.profiler.presentation.ui.screen.navigation.destination.ProfilerDestination.Companion.myNetworksStartRoute
import com.goforer.profiler.presentation.ui.screen.navigation.destination.ProfilerDestination.Companion.notificationHomeRoute
import com.goforer.profiler.presentation.ui.screen.navigation.destination.ProfilerDestination.Companion.notificationsStartRoute
import com.goforer.profiler.presentation.ui.screen.navigation.destination.ProfilerDestination.Companion.settingHomeRoute
import com.goforer.profiler.presentation.ui.screen.navigation.destination.ProfilerDestination.Companion.settingStartRoute
import com.goforer.profiler.presentation.ui.screen.navigation.ext.navigateSingleTopToGraph
import com.goforer.profiler.presentation.ui.screen.navigation.graph.communityGraph
import com.goforer.profiler.presentation.ui.screen.navigation.graph.networkGraph
import com.goforer.profiler.presentation.ui.screen.navigation.graph.notificationGraph
import com.goforer.profiler.presentation.ui.screen.navigation.graph.settingGraph
import com.goforer.profiler.presentation.ui.theme.ColorBottomBar
import com.goforer.profiler.presentation.ui.theme.ProfilerTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

val ProfilerScreens = listOf(MyNetworks, Community, DetailInfo, Notifications, Content, Setting)

@Stable
sealed class BottomNavItem(val route: String, @DrawableRes val icon: Int, @StringRes val title: Int) {
    object Profile : BottomNavItem(myNetworkHomeRoute, R.drawable.ic_profile, R.string.my_network)
    object Community :  BottomNavItem(communityHomeRoute, R.drawable.ic_community, R.string.community)
    object Notification :  BottomNavItem(notificationHomeRoute, R.drawable.ic_notification, R.string.notification)
    object Setting : BottomNavItem(settingHomeRoute, R.drawable.ic_setting, R.string.setting)
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ProfilerHomeScreen(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberAnimatedNavController()
) {
    var bottomBarVisible by remember { mutableStateOf(false) }
    val bottomBarOffset by animateDpAsState(targetValue = if (bottomBarVisible) 0.dp else 56.dp)
    val showBottomBar = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                val items = listOf(
                    BottomNavItem.Profile ,
                    BottomNavItem.Community,
                    BottomNavItem.Notification,
                    BottomNavItem.Setting,
                )

                BottomNavigation(
                    backgroundColor = ColorBottomBar,
                    contentColor = Color(0xFF3F414E),
                    elevation = 5.dp,
                    modifier = if (bottomBarVisible)
                        modifier.navigationBarsPadding()
                    else
                        modifier.offset(y = bottomBarOffset)
                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination

                    items.forEach { item ->
                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    painter = painterResource(id = item.icon),
                                    contentDescription = stringResource(id = item.title)
                                )
                            },
                            label = {
                                Text(
                                    stringResource(id = item.title),
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 13.sp
                                )
                            },
                            selectedContentColor = MaterialTheme.colorScheme.primary,
                            unselectedContentColor = Gray,
                            selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                            alwaysShowLabel = false,
                            onClick = {
                                navController.navigateSingleTopToGraph(item.route)
                            }
                        )
                    }
                }
            } else {
                // To Do : In case of Tablet, you've to implement here
            }
        },
        content = { innerPadding ->
            BoxWithConstraints(
                Modifier.padding(
                    start = innerPadding.calculateStartPadding(LayoutDirection.Ltr),
                    top = 0.dp,
                    end = innerPadding.calculateEndPadding(LayoutDirection.Ltr),
                    bottom = if (bottomBarVisible)
                        innerPadding.calculateBottomPadding()
                    else
                        0.dp)
            ) {
                AnimatedNavHost(
                    navController = navController,
                    startDestination = myNetworkHomeRoute
                ) {
                    networkGraph(
                        navController = navController,
                        startDestination =  myNetworksStartRoute,
                        route = myNetworkHomeRoute
                    )
                    communityGraph(
                        navController = navController,
                        startDestination = communitiesStartRoute,
                        route = communityHomeRoute
                    )
                    notificationGraph(
                        navController = navController,
                        startDestination = notificationsStartRoute,
                        route = notificationHomeRoute
                    )
                    settingGraph(
                        navController = navController,
                        startDestination = settingStartRoute,
                        route = settingHomeRoute
                    )
                }
            }
        }
    )

    navController.addOnDestinationChangedListener { _, destination, _ ->
        bottomBarVisible = when(destination.route) {
            myNetworksStartRoute, communitiesStartRoute, notificationsStartRoute, settingStartRoute -> {
                true
            }

            else -> {
                false
            }
        }
    }
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode",
    showSystemUi = true
)
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ProfilerHomeScreenPreview(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberAnimatedNavController()
) {
    ProfilerTheme {
        Scaffold(
            bottomBar = {
                val items = listOf(
                    BottomNavItem.Profile ,
                    BottomNavItem.Community,
                    BottomNavItem.Notification,
                    BottomNavItem.Setting,
                )

                BottomNavigation(
                    backgroundColor = ColorBottomBar,
                    contentColor = Color(0xFF3F414E),
                    elevation = 5.dp,
                    modifier = Modifier
                        .offset(y = 0.dp)
                        .navigationBarsPadding()
                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination

                    items.forEach { item ->
                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    painter = painterResource(id = item.icon),
                                    contentDescription = stringResource(id = item.title)
                                )
                            },
                            label = {
                                Text(
                                    stringResource(id = item.title),
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 8.sp
                                )
                            },
                            selectedContentColor = MaterialTheme.colorScheme.primary,
                            unselectedContentColor = Gray,
                            selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                            alwaysShowLabel = true,
                            onClick = {
                                navController.navigateSingleTopToGraph(item.route)
                            }
                        )
                    }
                }
            },
            content = { innerPadding ->
                AnimatedNavHost(
                    navController = navController,
                    startDestination = myNetworkHomeRoute,
                    modifier = modifier.padding(0.dp, 0.dp, 0.dp, innerPadding.calculateBottomPadding()),
                ) {
                    networkGraph(
                        navController = navController,
                        startDestination =  myNetworksStartRoute,
                        route = myNetworkHomeRoute
                    )
                    communityGraph(
                        navController = navController,
                        startDestination = communitiesStartRoute,
                        route = communityHomeRoute
                    )
                    notificationGraph(
                        navController = navController,
                        startDestination = notificationsStartRoute,
                        route = notificationHomeRoute
                    )
                    settingGraph(
                        navController = navController,
                        startDestination = settingStartRoute,
                        route = settingHomeRoute
                    )
                }
            }
        )
    }
}