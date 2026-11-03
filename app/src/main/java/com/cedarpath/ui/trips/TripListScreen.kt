package com.cedarpath.ui.trips

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cedarpath.data.Trip
import com.cedarpath.ui.theme.CedarPathTheme

// Shows the upcoming trips. It only displays data and reports taps.
@Composable
fun TripListScreen(
    trips: List<Trip>,
    onAddTrip: () -> Unit,
    onOpenTrip: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Upcoming trips", style = MaterialTheme.typography.headlineSmall)
        Button(onClick = onAddTrip) {
            Text("Add trip")
        }
        if (trips.isEmpty()) {
            Text("No upcoming trips yet.")
        }
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(trips, key = { it.id }) { trip ->
                TripRow(trip = trip, onOpen = { onOpenTrip(trip.id) })
            }
        }
    }
}

// One trip in the list: its name, date and difficulty, and an Open button.
@Composable
fun TripRow(trip: Trip, onOpen: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(trip.routeName)
            Text("${trip.date} - ${trip.difficulty}")
        }
        Button(onClick = onOpen) {
            Text("Open")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TripListScreenPreview() {
    CedarPathTheme {
        TripListScreen(
            trips = listOf(
                Trip(id = 1, routeName = "Qadisha Valley", date = "2026-11-15", difficulty = "Medium"),
                Trip(id = 2, routeName = "Chouf Cedars", date = "2026-11-22", difficulty = "Easy")
            ),
            onAddTrip = { },
            onOpenTrip = { }
        )
    }
}
