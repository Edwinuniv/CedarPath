package com.cedarpath.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cedarpath.data.ChecklistItem
import com.cedarpath.data.ChecklistRepository
import com.cedarpath.data.TripRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

// Holds one trip and its checklist for the detail screen,
// and saves the changes the user makes to the checklist.
class TripDetailViewModel(
    private val tripId: Int,
    tripRepository: TripRepository,
    private val checklistRepository: ChecklistRepository
) : ViewModel() {

    // The trip shown on the screen. Starts as null until Room answers.
    val trip = tripRepository.tripById(tripId).stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        null
    )

    // The checklist items of this trip. Starts as an empty list.
    val items = checklistRepository.itemsForTrip(tripId).stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        emptyList()
    )

    // Called when the user taps a checkbox.
    fun toggle(item: ChecklistItem) {
        viewModelScope.launch {
            checklistRepository.setChecked(item.id, !item.isChecked)
        }
    }

    // Called when the user adds a new line to the checklist.
    fun addItem(label: String) {
        viewModelScope.launch {
            checklistRepository.addItem(ChecklistItem(tripId = tripId, label = label))
        }
    }
}
