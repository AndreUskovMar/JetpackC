package ru.auskov.jetpackc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val searchState = remember {
                mutableStateOf("")
            }

            val isActive = remember {
                mutableStateOf(false)
            }

            val searchNamesList = remember {
                mutableStateOf(Utils.usersNameList)
            }

            setContent {
                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    colors = SearchBarDefaults.colors(
                        containerColor = Color.Black,
                        inputFieldColors = TextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                        )
                    ),
                    placeholder = {
                        Text(text = "Search...")
                    },
                    query = searchState.value,
                    onQueryChange = { text ->
                        searchState.value = text
                    },
                    onSearch = { text ->
                        searchNamesList.value = Utils.search(text)
                    },
                    active = isActive.value,
                    onActiveChange = {
                        isActive.value = it
                    }
                ) {
                    LazyColumn {
                        items(searchNamesList.value) {name ->
                            Box (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                            ){
                                Text(text = name, color = Color.Blue)
                            }
                        }
                    }
                }
            }
        }
    }
}

object Utils {
    val usersNameList = listOf(
        "Andre",
        "Sergio",
        "Alexandro",
        "Nikola",
        "Iren",
        "Tanya",
        "Olga",
        "Nataly"
    )

    fun search(value: String): List<String> {
        return usersNameList.filter {name ->
            name.lowercase().startsWith(value.lowercase())
        }
    }
}