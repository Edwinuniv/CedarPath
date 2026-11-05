package com.cedarpath.data

import kotlinx.coroutines.flow.Flow

// The one place the ViewModels ask for checklist data. It calls the DAO.
class ChecklistRepository(private val dao: ChecklistItemDao) {

    fun itemsForTrip(tripId: Int): Flow<List<ChecklistItem>> = dao.getItemsForTrip(tripId)

    suspend fun addItem(item: ChecklistItem) = dao.insert(item)

    suspend fun setChecked(id: Int, isChecked: Boolean) = dao.updateChecked(id, isChecked)
}
