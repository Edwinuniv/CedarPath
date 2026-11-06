package com.cedarpath.data

import androidx.room.Entity
import androidx.room.PrimaryKey

// One hiking trip, saved as one row in the "trips" table.
@Entity(tableName = "trips")
data class Trip(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val routeName: String,
    val date: String, // typed as "YYYY-MM-DD" so trips sort by date
    val difficulty: String, // "Easy", "Medium" or "Hard"
    val isCompleted: Boolean = false
)
