package ru.auskov.jetpackc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import ru.auskov.jetpackc.ui.theme.JetpackCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackCTheme {
                GreetingPreview()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (text, button, image) = createRefs()
        val bottomGuideline = createGuidelineFromBottom(0.2F)

        Button(onClick = {
            /*TODO*/
        },
            modifier = Modifier.constrainAs(button) {
                // top.linkTo(parent.top)
                bottom.linkTo(bottomGuideline)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            Text(text = "Click me")
        }

        Text(text = "Hello World!", modifier = Modifier.constrainAs(text) {
            bottom.linkTo(button.top, 25.dp)
            start.linkTo(button.start)
            end.linkTo(button.end)
        })

        Image(
            painter = painterResource(id = R.drawable.kotlin),
            contentDescription = "kotlin",
            modifier = Modifier.constrainAs(image) {
                bottom.linkTo(text.top)
                start.linkTo(button.start)
                end.linkTo(button.end)
            }
        )
    }
}