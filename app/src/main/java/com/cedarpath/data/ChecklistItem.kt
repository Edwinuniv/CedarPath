package com.cedarpath.data

import androidx.room.Entity
import androidx.room.PrimaryKey

// One line of a trip's checklist (for example "Water bottle"),
// saved as one row in the "checklist_items" table.
// tripId links the item to the Trip it belongs to.
@Entity(tableName = "checklist_items")
data class ChecklistItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val tripId: Int,
    val label: String,
    val isChecked: Boolean = false
)
