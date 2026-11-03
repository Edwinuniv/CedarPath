package com.cedarpath.data

import kotlinx.coroutines.flow.Flow

// The one place the ViewModels ask for trip data. It calls the DAO.
class TripRepository(private val dao: TripDao) {

    val upcomingTrips: Flow<List<Trip>> = dao.getUpcomingTrips()

    suspend fun addTrip(trip: Trip) = dao.insert(trip)
}
