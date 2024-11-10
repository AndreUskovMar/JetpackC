package ru.auskov.jetpackc

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.auskov.jetpackc.components.ListItem

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = viewModel(factory = MainViewModel.factory)
) {
    val listItems = mainViewModel.listItems.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = mainViewModel.text.value,
                onValueChange = {
                    mainViewModel.text.value = it
                },
                label = {
                    Text(text = "Name...")
                },
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = {
                if (mainViewModel.text.value != "")
                    mainViewModel.insertItem()
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(listItems.value) { item ->
                ListItem(item, onClick = {
                    mainViewModel.nameEntity = it
                    mainViewModel.text.value = it.name
                }, onDelete = {
                    mainViewModel.deleteItem(it)
                })
            }
        }
    }
}