package com.example.tipcalculatorcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipcalculatorcompose.ui.theme.TipCalculatorComposeTheme
import kotlin.math.ceil
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalculatorComposeTheme {
                TipTimeLayout()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipTimeLayout(modifier: Modifier = Modifier) {
    var tipAmount by remember { mutableStateOf("") }
    var roundUp by remember { mutableStateOf(false) }

    val amount = tipAmount.toDoubleOrNull() ?: 0.0
    val tipPercent = listOf(15.0, 18.0, 20.0)

    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableDoubleStateOf(tipPercent[0]) }

    val tip = calculateTip(amount, selectedOption, roundUp)

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Calculate Tip",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // TextField untuk Bill Amount
        TextField(
            value = tipAmount,
            onValueChange = { tipAmount = it },
            label = { Text("Bill Amount") },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                focusedIndicatorColor = Color(0xFFCE93D8),
                unfocusedIndicatorColor = Color(0xFFCE93D8)
            ),

            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Dropdown untuk Tip Percentage
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            TextField(
                readOnly = true,
                value = "${selectedOption.toInt()}%",
                onValueChange = {},
                label = { Text(stringResource(id = R.string.the_service)) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    focusedIndicatorColor = Color(0xFFCE93D8),
                    unfocusedIndicatorColor = Color(0xFFCE93D8)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                tipPercent.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text("${selectionOption.toInt()}%") },
                        onClick = {
                            selectedOption = selectionOption
                            expanded = false
                        }
                    )
                }
            }
        }

        // Switch untuk Round Up
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Round up tip?")
            Switch(
                checked = roundUp,
                onCheckedChange = { roundUp = it }
            )
        }

        // Menampilkan Tip Amount
        Text(
            text = "Tip Amount: $${"%.2f".format(tip)}",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

// Fungsi hitung tip
fun calculateTip(amount: Double, tipPercent: Double, roundUp: Boolean): Double {
    var tip = tipPercent / 100 * amount
    if (roundUp) {
        tip = ceil(tip)
    }
    return tip
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TipCalculatorComposeTheme {
        TipTimeLayout()
    }
}
