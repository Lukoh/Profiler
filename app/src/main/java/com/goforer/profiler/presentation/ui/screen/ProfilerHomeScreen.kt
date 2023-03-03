package com.goforer.profiler.presentation.ui.screen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.*
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.goforer.profiler.R
import com.goforer.profiler.presentation.ui.MainActivity.Companion.communityRoutes
import com.goforer.profiler.presentation.ui.MainActivity.Companion.navigationRoutes
import com.goforer.profiler.presentation.ui.MainActivity.Companion.notificationRoutes
import com.goforer.profiler.presentation.ui.MainActivity.Companion.profileRoutes
import com.goforer.profiler.presentation.ui.MainActivity.Companion.settingRoutes
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

sealed class BottomNavItem(val route: String, @DrawableRes val icon: Int, @StringRes val title: Int) {
    object Profile : BottomNavItem(navigationRoutes[0], R.drawable.ic_profile, R.string.profile)
    object Community :  BottomNavItem(navigationRoutes[1], R.drawable.ic_community, R.string.community)
    object Notification :  BottomNavItem(navigationRoutes[2], R.drawable.ic_notification, R.string.notification)
    object Setting : BottomNavItem(navigationRoutes[3], R.drawable.ic_setting, R.string.setting)
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ProfilerHomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberAnimatedNavController()
) {
    var isVisible by rememberSaveable { mutableStateOf(true) }

    Scaffold(
        bottomBar = {
            val items = listOf(
                BottomNavItem.Profile ,
                BottomNavItem.Community,
                BottomNavItem.Notification,
                BottomNavItem.Setting,
            )
            AnimatedVisibility(
                visible = isVisible,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it }),
                content = {
                    BottomNavigation(
                        backgroundColor = colorResource(id = R.color.bottom_bar),
                        contentColor = Color(0xFF3F414E),
                        elevation = 5.dp,
                        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 24.dp)
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
                                alwaysShowLabel = true,
                                onClick = {
                                    navController.navigateSingleTopTo(item.route)
                                }
                            )
                        }
                    }
                }
            )
        },
        content = { innerPadding ->
            AnimatedNavHost(
                navController = navController,
                startDestination = BottomNavItem.Profile.route,
                modifier = modifier.padding(0.dp, 0.dp, 0.dp, innerPadding.calculateBottomPadding())
            ) {
                profileGraph(navController, navigationRoute = BottomNavItem.Profile.route, startDestination = Profiles.route)
                communityGraph(navController, navigationRoute = BottomNavItem.Community.route, startDestination = Community.route)
                notificationGraph(navController, navigationRoute = BottomNavItem.Notification.route, startDestination = Notification.route)
                settingGraph(navController, navigationRoute = BottomNavItem.Setting.route, startDestination = Setting.route)
            }
        }
    )

    navController.addOnDestinationChangedListener { _, destination, _ ->
        isVisible = when(destination.route) {
            profileRoutes[0], communityRoutes[0], notificationRoutes[0], settingRoutes[0] -> {
                true
            }

            else -> {
                false
            }
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }

        launchSingleTop = true
        restoreState = true
    }