package ru.auskov.jetpackc.navigator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Home () {
    Text(
        text = "Home",
        modifier = Modifier.fillMaxSize().wrapContentHeight(),
        textAlign = TextAlign.Center
    )
}

@Composable
fun Feed () {
    Text(
        text = "Feed",
        modifier = Modifier.fillMaxSize().wrapContentHeight(),
        textAlign = TextAlign.Center
    )
}
@Composable
fun Actions () {
    Text(
        text = "Actions",
        modifier = Modifier.fillMaxSize().wrapContentHeight(),
        textAlign = TextAlign.Center
    )
}
@Composable
fun Profile () {
    Text(
        text = "Profile",
        modifier = Modifier.fillMaxSize().wrapContentHeight(),
        textAlign = TextAlign.Center
    )
}