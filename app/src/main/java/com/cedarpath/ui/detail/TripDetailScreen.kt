package com.cedarpath.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cedarpath.data.ChecklistItem
import com.cedarpath.data.Trip
import com.cedarpath.ui.theme.CedarPathTheme

// Shows one trip and its checklist. It only displays data and reports taps.
@Composable
fun TripDetailScreen(
    trip: Trip?,
    items: List<ChecklistItem>,
    onToggleItem: (ChecklistItem) -> Unit,
    onAddItem: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    // The text typed in the "new item" field. It survives rotation.
    var newLabel by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (trip == null) {
            Text("Trip not found.")
            return@Column
        }
        Text(trip.routeName, style = MaterialTheme.typography.headlineSmall)
        Text("${trip.date} - ${trip.difficulty}")

        Text("Checklist", style = MaterialTheme.typography.titleMedium)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = newLabel,
                onValueChange = { newLabel = it },
                label = { Text("New item") },
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = {
                    onAddItem(newLabel.trim())
                    newLabel = ""
                },
                enabled = newLabel.isNotBlank()
            ) {
                Text("Add")
            }
        }
        if (items.isEmpty()) {
            Text("No items yet.")
        }
        LazyColumn {
            items(items, key = { it.id }) { item ->
                ChecklistRow(item = item, onToggle = { onToggleItem(item) })
            }
        }
    }
}

// One checklist line: a checkbox and its label.
@Composable
fun ChecklistRow(item: ChecklistItem, onToggle: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = item.isChecked, onCheckedChange = { onToggle() })
        Text(item.label)
    }
}

@Preview(showBackground = true)
@Composable
fun TripDetailScreenPreview() {
    CedarPathTheme {
        TripDetailScreen(
            trip = Trip(id = 1, routeName = "Qadisha Valley", date = "2026-11-15", difficulty = "Medium"),
            items = listOf(
                ChecklistItem(id = 1, tripId = 1, label = "Water bottle", isChecked = true),
                ChecklistItem(id = 2, tripId = 1, label = "Headlamp")
            ),
            onToggleItem = { },
            onAddItem = { }
        )
    }
}
