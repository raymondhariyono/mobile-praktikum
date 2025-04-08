package com.example.dicerollercompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dicerollercompose.ui.theme.DiceRollerComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceRollerComposeTheme {
                DiceRollerApp()
            }
        }
    }
}

@Composable
fun DiceRollerApp() {
    DiceWithButtonImageAndMessage()
}


@Composable
fun DiceWithButtonImageAndMessage() {
    var resultLeft by remember { mutableIntStateOf(0) }
    var resultRight by remember { mutableIntStateOf(0) }
    var messageResult by remember { mutableStateOf("") }

    val imageResourceLeft = when (resultLeft) {
        0 -> R.drawable.dice_0
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    val imageResourceRight = when (resultRight) {
        0 -> R.drawable.dice_0
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.safeDrawing.asPaddingValues())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(imageResourceLeft),
                contentDescription = "Dice $resultLeft",
                modifier = Modifier.size(200.dp)
            )
            Image(
                painter = painterResource(imageResourceRight),
                contentDescription = "Dice $resultRight",
                modifier = Modifier.size(200.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                resultLeft = (1..6).random()
                resultRight = (1..6).random()

                messageResult = if (resultLeft == resultRight) {
                    "Selamat, Anda dapat dadu double"
                } else {
                    "Anda belum beruntung, coba lagi!"
                }
            }
        ) {
            Text(stringResource(R.string.roll))
        }

        Spacer(modifier = Modifier.weight(1f))

        if (messageResult.isNotEmpty()) {
            Text(
                text = messageResult,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFF3E5F5))
                    .padding(16.dp)
            )
        } else {
            Spacer(modifier = Modifier.height(56.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDiceRollerApp() {
    DiceRollerComposeTheme {
        DiceRollerApp()
    }
}
