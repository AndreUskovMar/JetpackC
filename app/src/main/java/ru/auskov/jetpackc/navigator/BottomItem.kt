package ru.auskov.jetpackc.navigator

import ru.auskov.jetpackc.R

sealed class BottomItem(val title: String, val iconId: Int, val route: String) {
    data object Home: BottomItem("Home", R.drawable.home, "home")
    data object Feed: BottomItem("Feed", R.drawable.feed, "feed")
    data object Actions: BottomItem("Actions", R.drawable.heart, "actions")
    data object Profile: BottomItem("Profile", R.drawable.profile, "profile")
}