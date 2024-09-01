package ru.auskov.jetpackc

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.auskov.jetpackc.ui.theme.JetpackCTheme
import java.io.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val coroutine = rememberCoroutineScope()
            val context = LocalContext.current

            val textState = remember {
                mutableStateOf("")
            }
            JetpackCTheme {
               Column(
                   modifier = Modifier.fillMaxSize(),
                   verticalArrangement = Arrangement.Center,
                   horizontalAlignment = Alignment.CenterHorizontally
               ) {
                   Text(text = textState.value)
                   Spacer(modifier = Modifier.height(30.dp))

                   Button(onClick = {
                       coroutine.launch {
                           textState.value = readFile(context).toString()
                       }
                   }) {
                       Text(text = "Read")
                   }
                   Spacer(modifier = Modifier.height(10.dp))
                   Button(onClick = {
                       coroutine.launch {
                           save(context)
                       }
                   }) {
                       Text(text = "Save")
                   }
               }
            }
            
        }
    }
}

private suspend fun save(context: Context) {
    val text = "Похуй, просто текст"
    withContext(Dispatchers.IO){
        context.openFileOutput("test.txt", Context.MODE_PRIVATE).use {
            it.write(text.toByteArray())
        }
    }
}

private suspend fun readFile(context: Context) = withContext(Dispatchers.IO) {
    try {
        context.openFileInput("test.txt").bufferedReader().useLines { lines ->
            lines.fold("") { acc, s ->
                "$acc\n$s"
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
        ""
    }
}