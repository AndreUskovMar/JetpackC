package ru.auskov.jetpackc

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun Drawer() {
    val items = listOf(
        DrawerItem(Icons.Default.Favorite, "Избранное"),
        DrawerItem(Icons.Default.Add, "Добавить"),
        DrawerItem(Icons.Default.Person, "Профиль"),
    )

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutine = rememberCoroutineScope()

    val selectedItem = remember {
        mutableStateOf(items[0])
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Image(
                    painter = painterResource(id = R.drawable.back_drop),
                    contentDescription = "bg",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(20.dp))

                items.forEach { item ->
                    NavigationDrawerItem(
                        modifier = Modifier.padding(5.dp),
                        label = {
                           Text(text = item.title)
                        },
                        icon = {
                           Icon(imageVector = item.icon, contentDescription = "icon-${item.title}")
                        },
                        selected = item == selectedItem.value,
                        onClick = {
                            coroutine.launch {
                                selectedItem.value = item
                                drawerState.close()
                            }
                        }
                    )
                }
            }
        },
        content = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = {
                    coroutine.launch {
                        drawerState.open()
                    }
                }) {
                    Text(text = "Нажми сюда")
                }
            }
        }
    )
}

data class DrawerItem(
    val icon: ImageVector,
    val title: String
)