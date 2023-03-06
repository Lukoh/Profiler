package com.goforer.profiler.presentation.ui.screen.navigation.ext

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

fun NavHostController.navigateSingleTopToGraph(route: String) =
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }

        launchSingleTop = true
        restoreState = true
    }

fun NavHostController.navigateSingleTopTo(route: String) =
    navigate(route) {
        popUpTo(route) {
            saveState = true
        }

        launchSingleTop = true
        restoreState = true
    }