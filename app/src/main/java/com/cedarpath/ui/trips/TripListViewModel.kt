package com.cedarpath.ui.trips

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cedarpath.data.Trip
import com.cedarpath.data.TripRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

// Holds the trip list for the screens and saves new trips.
class TripListViewModel(private val repository: TripRepository) : ViewModel() {

    // Upcoming trips as state the screen can collect. Starts as an empty list.
    val upcomingTrips = repository.upcomingTrips.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        emptyList()
    )

    // Called by the trip form when the user taps Save.
    fun addTrip(routeName: String, date: String, difficulty: String) {
        viewModelScope.launch {
            repository.addTrip(
                Trip(routeName = routeName, date = date, difficulty = difficulty)
            )
        }
    }
}
