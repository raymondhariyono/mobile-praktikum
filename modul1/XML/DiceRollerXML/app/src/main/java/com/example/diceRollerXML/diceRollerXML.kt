package com.example.modul1

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity


class diceRollerXML : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonRoll = findViewById<Button>(R.id.buttonRoll)
        val diceImageLeft = findViewById<ImageView>(R.id.DiceLeft)
        val diceImageRight = findViewById<ImageView>(R.id.DiceRight)
        val resultMessage = findViewById<TextView>(R.id.resultMessage)


        diceImageLeft.setImageResource(R.drawable.dice_0)
        diceImageRight.setImageResource(R.drawable.dice_0)

        buttonRoll.setOnClickListener {
            val randomNumberLeft = (1..6).random()
            val randomNumberRight = (1..6).random()

            val drawableResourceLeft = when (randomNumberLeft) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }
            val drawableResourceRight = when (randomNumberRight) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }
            diceImageRight .setImageResource(drawableResourceRight)
            diceImageLeft.setImageResource(drawableResourceLeft)

            if (randomNumberLeft == randomNumberRight) {
                resultMessage.text = "Selamat, anda mendapat dadu double!"
                resultMessage.setBackgroundResource(R.drawable.rounded_border_win_textview)
            } else {
                resultMessage.text = "Anda Belum beruntung"
                resultMessage.setBackgroundResource(R.drawable.rounded_border_lose_textview)

            }
        }
    }
}
