package com.cedarpath.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// The database operations we need for trips.
@Dao
interface TripDao {

    // Saves a new trip. Room creates its id.
    @Insert
    suspend fun insert(trip: Trip)

    // All trips that are not completed yet, the nearest date first.
    @Query("SELECT * FROM trips WHERE isCompleted = 0 ORDER BY date")
    fun getUpcomingTrips(): Flow<List<Trip>>

    // One trip found by its id, for the detail screen. Null if it does not exist.
    @Query("SELECT * FROM trips WHERE id = :id")
    fun getTripById(id: Int): Flow<Trip?>
}
