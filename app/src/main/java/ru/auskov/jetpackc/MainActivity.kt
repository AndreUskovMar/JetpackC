package ru.auskov.jetpackc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import ru.auskov.jetpackc.ui.theme.JetpackCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackCTheme {
                val composition by rememberLottieComposition(
                    spec = LottieCompositionSpec.Asset("waiting.json")
                )

                val animSpec = LottieClipSpec.Progress(
                    0f,
                    0.5f
                )

                //val progress by animateLottieCompositionAsState(composition)

                var isPlaying by remember {
                    mutableStateOf(false)
                }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    LottieAnimation(
                        composition = composition,
                        isPlaying = isPlaying,
                        // iterations = LottieConstants.IterateForever,
                        iterations = 3,
                        reverseOnRepeat = true,
                        clipSpec = animSpec
                    )

                    Button(onClick = {
                        isPlaying = true
                    }) {
                        Text(text = "Start", color = Color.White)
                    }
                }
            }
        }
    }
}