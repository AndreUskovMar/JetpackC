package ru.auskov.jetpackc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListItem(name = "Vasya Pupkin", profession = "engineer")
        }
    }
}

@Composable
fun ListItem(name: String, profession: String) {
    val counter = remember {
        mutableIntStateOf(0)
    };

    val buttonColor = remember {
        mutableStateOf(Color.Green)
    }

    fun increment() {
        when(++counter.intValue) {
            10 -> buttonColor.value = Color.Red
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Box(){
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "android",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(5.dp)
                        .size(64.dp)
                        .clip(CircleShape)
                )
                
                Column(modifier = Modifier.padding(start = 10.dp)) {
                    Text(
                        text = name,
                        fontSize = 18.sp,
                        color = Color.Red,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = profession, fontSize = TextUnit.Unspecified)
                }

                Box(
                    modifier = Modifier
                        .padding(start = 50.dp)
                        .size(50.dp)
                        .background(buttonColor.value)
                        .clickable { increment() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = counter.intValue.toString(),
                        fontSize = 28.sp, color = Color.Blue
                    )
                }
            }
        }
    }
}