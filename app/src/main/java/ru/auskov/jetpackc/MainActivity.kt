package ru.auskov.jetpackc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import ru.auskov.jetpackc.ui.theme.Blue
import ru.auskov.jetpackc.ui.theme.Green
import ru.auskov.jetpackc.ui.theme.JetpackCTheme
import ru.auskov.jetpackc.ui.theme.Red

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataStore = DataStoreManager(this)

        setContent {
            val bgColorState = remember {
                mutableStateOf(Blue.value)
            }
            val fontSizeState = remember {
                mutableIntStateOf(40)
            }

            LaunchedEffect(key1 = true) {
                dataStore.getSettings().collect{ settings ->
                    bgColorState.value = settings.bgColor.toULong()
                    fontSizeState.intValue = settings.fontSize
                }
            }

            JetpackCTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(bgColorState.value)
                ) {
                    Greeting(dataStore, fontSizeState)
                }
            }
        }
    }
}

@Composable
fun Greeting(dataStore: DataStoreManager, fontSizeState: MutableState<Int>) {
    val coroutine = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(0.5f)
                .wrapContentSize(align = Alignment.Center)
        ) {
            Text(text = "Some text", color = Color.White, fontSize = fontSizeState.value.sp)
        }

        Button(onClick = {
            coroutine.launch {
                dataStore.saveDataSettings(
                    DataSettings(50, Red.value.toLong())
                )
            }
        }) {
            Text(text = "Red", color = Color.White)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            coroutine.launch {
                dataStore.saveDataSettings(
                    DataSettings(20, Green.value.toLong())
                )
            }
        }) {
            Text(text = "Green", color = Color.White)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            coroutine.launch {
                dataStore.saveDataSettings(
                    DataSettings(40, Blue.value.toLong())
                )
            }
        }) {
            Text(text = "Blue", color = Color.White)
        }
    }
}