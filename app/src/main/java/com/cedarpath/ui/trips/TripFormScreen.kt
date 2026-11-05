package com.cedarpath.ui.trips

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cedarpath.ui.theme.CedarPathTheme

// The form to create a trip. It keeps the typed text and reports Save.
@Composable
fun TripFormScreen(
    onSave: (routeName: String, date: String, difficulty: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var routeName by rememberSaveable { mutableStateOf("") }
    var date by rememberSaveable { mutableStateOf("") }
    var difficulty by rememberSaveable { mutableStateOf("Easy") }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("New trip", style = MaterialTheme.typography.headlineSmall)
        OutlinedTextField(
            value = routeName,
            onValueChange = { routeName = it },
            label = { Text("Route name") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = date,
            onValueChange = { date = it },
            label = { Text("Date (YYYY-MM-DD)") },
            modifier = Modifier.fillMaxWidth()
        )
        Text("Difficulty: $difficulty")
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { difficulty = "Easy" }) { Text("Easy") }
            Button(onClick = { difficulty = "Medium" }) { Text("Medium") }
            Button(onClick = { difficulty = "Hard" }) { Text("Hard") }
        }
        Button(
            onClick = { onSave(routeName, date, difficulty) },
            enabled = routeName.isNotBlank() && date.isNotBlank()
        ) {
            Text("Save trip")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TripFormScreenPreview() {
    CedarPathTheme {
        TripFormScreen(onSave = { _, _, _ -> })
    }
}
