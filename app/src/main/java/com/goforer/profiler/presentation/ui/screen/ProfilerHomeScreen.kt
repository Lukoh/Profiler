package com.goforer.profiler.presentation.ui.screen

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
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.*
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.goforer.profiler.R
import com.goforer.profiler.presentation.ui.ext.navigateSingleTopToGraph
import com.goforer.profiler.presentation.ui.screen.navgraph.CommunityNav.communitiesStartRoute
import com.goforer.profiler.presentation.ui.screen.navgraph.CommunityNav.communityHomeRoute
import com.goforer.profiler.presentation.ui.screen.navgraph.MyNetworkNav.myNetworkHomeRoute
import com.goforer.profiler.presentation.ui.screen.navgraph.MyNetworkNav.myNetworksStartRoute
import com.goforer.profiler.presentation.ui.screen.navgraph.NotificationNav.notificationHomeRoute
import com.goforer.profiler.presentation.ui.screen.navgraph.NotificationNav.notificationsStartRoute
import com.goforer.profiler.presentation.ui.screen.navgraph.SettingNav.settingHomeRoute
import com.goforer.profiler.presentation.ui.screen.navgraph.SettingNav.settingStartRoute
import com.goforer.profiler.presentation.ui.screen.navgraph.communityGraph
import com.goforer.profiler.presentation.ui.screen.navgraph.networkGraph
import com.goforer.profiler.presentation.ui.screen.navgraph.notificationGraph
import com.goforer.profiler.presentation.ui.screen.navgraph.settingGraph
import com.goforer.profiler.presentation.ui.theme.ColorBottomBar
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

sealed class BottomNavItem(val route: String, @DrawableRes val icon: Int, @StringRes val title: Int) {
    object Profile : BottomNavItem(myNetworkHomeRoute, R.drawable.ic_profile, R.string.my_network)
    object Community :  BottomNavItem(communityHomeRoute, R.drawable.ic_community, R.string.community)
    object Notification :  BottomNavItem(notificationHomeRoute, R.drawable.ic_notification, R.string.notification)
    object Setting : BottomNavItem(settingHomeRoute, R.drawable.ic_setting, R.string.setting)
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ProfilerHomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberAnimatedNavController()
) {
    var bottomBarVisible by remember { mutableStateOf(false) }
    val bottomBarOffset by animateDpAsState(targetValue = if (bottomBarVisible) 0.dp else 56.dp)

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
                    .padding(0.dp, 0.dp, 0.dp, 24.dp)
                    .offset(y = bottomBarOffset)
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
                networkGraph(navController, myNetworksStartRoute, myNetworkHomeRoute)
                communityGraph(navController, communitiesStartRoute, communityHomeRoute)
                notificationGraph(navController, notificationsStartRoute, notificationHomeRoute)
                settingGraph(navController, settingStartRoute, settingHomeRoute)
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