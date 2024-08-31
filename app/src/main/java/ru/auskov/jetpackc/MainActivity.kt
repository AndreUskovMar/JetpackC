package ru.auskov.jetpackc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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

        // val dataStore = DataStoreManager(this)
        val dataStore = ProtoDataStoreManager(this)

        setContent {
            val settings = dataStore
                .getSettings()
                .collectAsState(DataSettings())

            JetpackCTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(settings.value.bgColor)
                ) {
                    Greeting(dataStore, settings.value.fontSize)
                }
            }
        }
    }
}

@Composable
fun Greeting(dataStore: ProtoDataStoreManager, fontSizeState: Int) {
    val coroutine = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .fillMaxWidth()
                .wrapContentSize(align = Alignment.Center)
        ) {
            Text(text = "Some text", color = Color.White, fontSize = fontSizeState.sp)
        }

        Button(onClick = {
            coroutine.launch {
                dataStore.saveDataSettings(
                    DataSettings(50, Red.value)
                )
            }
        }) {
            Text(text = "Red", color = Color.White)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            coroutine.launch {
                dataStore.saveDataSettings(
                    DataSettings(20, Green.value)
                )
            }
        }) {
            Text(text = "Green", color = Color.White)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            coroutine.launch {
                dataStore.saveDataSettings(
                    DataSettings(40, Blue.value)
                )
            }
        }) {
            Text(text = "Blue", color = Color.White)
        }
    }
}