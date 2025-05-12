package com.raymondHariyono.playcut.screens

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.raymondHariyono.playcut.data.Barber

@Composable
fun BookingPage(
    navController: NavController,
    barber: Barber,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    var selectedTime by rememberSaveable { mutableStateOf("") }
    var selectedService by rememberSaveable { mutableStateOf("") }
    var selectedAdditionalService by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Book with ${barber.name}",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text("Select Service", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        ServicesSelectionDropDown { selectedService = it }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Select Additional Service", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        AdditionalServicesGrid { selectedAdditionalService = it }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Select Available Time", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        TimeSelectionGrid(barber = barber) { selectedTime = it }

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                if (selectedTime.isEmpty() || selectedService.isEmpty() || selectedAdditionalService.isEmpty()) {
                    Toast.makeText(context, "Please select service, additional service, and time", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Booking confirmed with ${barber.name} at $selectedTime", Toast.LENGTH_SHORT).show()
                    onDismiss()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Confirm Booking", style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ServicesSelectionDropDown(onServiceSelected: (String) -> Unit) {
    val dropdownServices = listOf("Haircut", "Haircut + Keramas", "Haircut + Keramas + Treatment")
    var expanded by remember { mutableStateOf(false) }
    var selectedDropdownService by remember { mutableStateOf(dropdownServices.first()) }

    onServiceSelected(selectedDropdownService)

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            readOnly = true,
            value = selectedDropdownService,
            onValueChange = {},
            label = { Text("Service") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            dropdownServices.forEach { service ->
                DropdownMenuItem(
                    text = { Text(service) },
                    onClick = {
                        selectedDropdownService = service
                        onServiceSelected(service)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun AdditionalServicesGrid(onServiceSelected: (String) -> Unit) {
    val additionalServices = listOf("Coloring", "Shave", "Hair Spa", "Perming", "Smoothing", "Braids")
    var selectedService by remember { mutableStateOf(additionalServices.first()) }

    onServiceSelected(selectedService)

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(additionalServices) { service ->
            Card(
                modifier = Modifier
                    .height(60.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable {
                        selectedService = service
                        onServiceSelected(service)
                    }
                    .border(
                        width = if (selectedService == service) 2.dp else 1.dp,
                        color = if (selectedService == service) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant,
                        shape = RoundedCornerShape(8.dp)
                    ),
                colors = CardDefaults.cardColors(
                    containerColor = if (selectedService == service) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = service,
                        textAlign = TextAlign.Center,
                        color = if (selectedService == service) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@Composable
fun TimeSelectionGrid(barber: Barber, onTimeSelected: (String) -> Unit) {
    var selectedTime by rememberSaveable { mutableStateOf("") }

    onTimeSelected(selectedTime)

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        items(barber.availableTimes) { time ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        selectedTime = time
                        onTimeSelected(time)
                    }
                    .border(
                        width = if (selectedTime == time) 2.dp else 1.dp,
                        color = if (selectedTime == time) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant,
                        shape = RoundedCornerShape(8.dp)
                    ),
                colors = CardDefaults.cardColors(
                    containerColor = if (selectedTime == time) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = time,
                        color = if (selectedTime == time) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}
