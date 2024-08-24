package ru.auskov.jetpackc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import dagger.hilt.android.AndroidEntryPoint
import ru.auskov.jetpackc.frags.MainActivity2
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var wifiManager: WiFiManager
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("MyLog", "Instance activity 1 $wifiManager")
        mainViewModel.connect()

        fun navigateTo() {
            startActivity(Intent(this, MainActivity2::class.java))
        }

        setContent {
            GreetingText("World") { navigateTo() }
        }
    }
}

@Composable
fun GreetingText(name: String, callback: () -> Unit) {
    Button(onClick = {
        callback()
    }) {
        Text(text = "Hello $name!", color = MaterialTheme.colorScheme.error)
    }

}