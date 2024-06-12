package ru.auskov.jetpackc.navigator

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = "home") {
        composable("home") {
            Home()
        }
        composable("feed") {
            Feed()
        }
        composable("actions") {
            Actions()
        }
        composable("profile") {
            Profile()
        }
    }
}