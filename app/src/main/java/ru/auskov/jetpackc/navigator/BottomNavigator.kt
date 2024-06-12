package ru.auskov.jetpackc.navigator

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

private object NoRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor(): Color = Color.White

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(
        0.0f,
        0.0f,
        0.0f,
        0.0f
    )
}

@Composable
fun BottomNavigator(
    navController: NavController
) {
    val routes = listOf(
        BottomItem.Home,
        BottomItem.Feed,
        BottomItem.Actions,
        BottomItem.Profile
    )

    CompositionLocalProvider(
        LocalRippleTheme provides NoRippleTheme
    ) {
        NavigationBar(
            containerColor = Color.White
        ) {
            val backStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = backStackEntry?.destination?.route

            routes.forEach { item ->
                NavigationBarItem(
                    selected = item.route == currentRoute,
                    onClick = {
                        navController.navigate(item.route)
                    },
                    icon = {
                        Icon(painter = painterResource(item.iconId), contentDescription = "icon")
                    },
                    label = {
                        Text(text = item.title, fontSize = 14.sp)
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Red,
                        selectedTextColor = Color.Red,
                        unselectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Gray,
                        indicatorColor = Color.White
                    )
                )
            }
        }
    }
}