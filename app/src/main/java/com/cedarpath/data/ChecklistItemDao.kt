package com.cedarpath.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// The database operations we need for a trip's checklist.
@Dao
interface ChecklistItemDao {

    // Saves a new checklist item. Room creates its id.
    @Insert
    suspend fun insert(item: ChecklistItem)

    // All the checklist items of one trip, in the order they were added.
    @Query("SELECT * FROM checklist_items WHERE tripId = :tripId ORDER BY id")
    fun getItemsForTrip(tripId: Int): Flow<List<ChecklistItem>>

    // Checks or unchecks one item.
    @Query("UPDATE checklist_items SET isChecked = :isChecked WHERE id = :id")
    suspend fun updateChecked(id: Int, isChecked: Boolean)
}
