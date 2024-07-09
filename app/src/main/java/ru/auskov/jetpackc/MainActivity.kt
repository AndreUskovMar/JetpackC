package ru.auskov.jetpackc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.auskov.jetpackc.screens.Screen1
import ru.auskov.jetpackc.screens.Screen2
import ru.auskov.jetpackc.screens.Screen3

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "screen_1") {
                composable("screen_1") {
                    Screen1 {
                        navController.navigate("screen_2")
                    }
                }
                composable("screen_2") {
                    Screen2 {
                        navController.navigate("screen_3")
                    }
                }
                composable("screen_3") {
                    Screen3 {
                        navController.popBackStack("screen_1", false)
                    }
                }
            }
        }
    }
}