package com.cedarpath.data

import androidx.room.Database
import androidx.room.RoomDatabase

// The Room database of the app. It lists every table (entity) it contains.
@Database(entities = [Trip::class, ChecklistItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tripDao(): TripDao
    abstract fun checklistItemDao(): ChecklistItemDao
}
