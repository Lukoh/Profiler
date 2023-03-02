package com.goforer.profiler.presentation.ui.screen

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ProfilerHomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberAnimatedNavController()
) {
    Scaffold(
        bottomBar = {
            val items = listOf(
                BottomNavItem.Profile ,
                BottomNavItem.Setting,
            )

            BottomNavigation(
                backgroundColor = Color.White,
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
        },
        content = { innerPadding ->
            AnimatedNavHost(
                navController = navController,
                startDestination = BottomNavItem.Profile.route,
                modifier = modifier.padding(0.dp, 0.dp, 0.dp, innerPadding.calculateBottomPadding())
            ) {
                profileGraph(navController, navigationRoute = BottomNavItem.Profile.route, startDestination = Profiles.route)
                settingGraph(navController, navigationRoute = BottomNavItem.Setting.route, startDestination = Setting.route)
            }
        }
    )
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