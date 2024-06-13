package ru.auskov.jetpackc.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.auskov.jetpackc.R

@OptIn(DelicateCoroutinesApi::class)
@Preview(showBackground = true)
@Composable
fun RuletaScreen() {
    val rotate = remember {
        Animatable(0f)
    }

    var repeatAnimation by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = repeatAnimation) {
        rotate.animateTo(
            targetValue = 1080f,
            animationSpec = tween(
                durationMillis = 3000,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "0",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {
            Image(
                painter = painterResource(R.drawable.ruleta),
                contentDescription = "ruleta",
                modifier = Modifier
                    .fillMaxSize()
                    .rotate(rotate.value)
            )
            Image(
                painter = painterResource(R.drawable.flecha),
                contentDescription = "flecha",
                modifier = Modifier
                    .padding(bottom = 260.dp)
                    .scale(0.1f)
                    .fillMaxSize()
            )
        }
        
        Button(
            onClick = {
                GlobalScope.launch {
                    rotate.snapTo(0f)
                }
                repeatAnimation = !repeatAnimation
            },
            colors = ButtonDefaults.buttonColors(Color.Red),
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Старт", color = Color.White)
        }
    }
}