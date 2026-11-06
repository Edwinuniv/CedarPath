package com.cedarpath.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cedarpath.ui.trips.TripFormScreen
import com.cedarpath.ui.trips.TripListScreen
import com.cedarpath.ui.trips.TripListViewModel

// Maps each route to its screen. The list ("trips") opens first.
@Composable
fun AppNavHost(
    navController: NavHostController,
    viewModel: TripListViewModel,
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
                onOpenTrip = { } // Feature 2 adds the "trip/{tripId}" detail route
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
    }
}
