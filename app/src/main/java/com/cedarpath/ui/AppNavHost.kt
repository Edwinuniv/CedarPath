package com.cedarpath.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.cedarpath.data.ChecklistRepository
import com.cedarpath.data.TripRepository
import com.cedarpath.ui.detail.TripDetailScreen
import com.cedarpath.ui.detail.TripDetailViewModel
import com.cedarpath.ui.trips.TripFormScreen
import com.cedarpath.ui.trips.TripListScreen
import com.cedarpath.ui.trips.TripListViewModel

// Maps each route to its screen. The list ("trips") opens first.
@Composable
fun AppNavHost(
    navController: NavHostController,
    viewModel: TripListViewModel,
    tripRepository: TripRepository,
    checklistRepository: ChecklistRepository,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "trips",
        modifier = modifier
    ) {
        composable("trips") {
            val trips by viewModel.upcomingTrips.collectAsStateWithLifecycle()
            TripListScreen(
                trips = trips,
                onAddTrip = { navController.navigate("trip-form") },
                onOpenTrip = { tripId -> navController.navigate("trip/$tripId") }
            )
        }
        composable("trip-form") {
            TripFormScreen(
                onSave = { routeName, date, difficulty ->
                    viewModel.addTrip(routeName, date, difficulty)
                    navController.popBackStack()
                }
            )
        }
        // Detail of one trip. The trip id travels in the route, for example "trip/3".
        composable(
            route = "trip/{tripId}",
            arguments = listOf(navArgument("tripId") { type = NavType.IntType })
        ) { backStackEntry ->
            val tripId = backStackEntry.arguments?.getInt("tripId") ?: 0
            // One ViewModel for this trip, kept while the screen is shown.
            val detailViewModel = remember(tripId) {
                TripDetailViewModel(tripId, tripRepository, checklistRepository)
            }
            val trip by detailViewModel.trip.collectAsStateWithLifecycle()
            val items by detailViewModel.items.collectAsStateWithLifecycle()
            TripDetailScreen(
                trip = trip,
                items = items,
                onToggleItem = { item -> detailViewModel.toggle(item) },
                onAddItem = { label -> detailViewModel.addItem(label) }
            )
        }
    }
}
