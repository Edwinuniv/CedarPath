package com.cedarpath

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.cedarpath.data.AppDatabase
import com.cedarpath.data.ChecklistRepository
import com.cedarpath.data.TripRepository
import com.cedarpath.ui.AppNavHost
import com.cedarpath.ui.theme.CedarPathTheme
import com.cedarpath.ui.trips.TripListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Build the database, then the repositories and ViewModel that use it.
        val db = Room.databaseBuilder(this, AppDatabase::class.java, "cedarpath.db").build()
        val tripRepository = TripRepository(db.tripDao())
        val checklistRepository = ChecklistRepository(db.checklistItemDao())
        val viewModel = TripListViewModel(tripRepository)

        setContent {
            CedarPathTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CedarPathApp(
                        viewModel = viewModel,
                        tripRepository = tripRepository,
                        checklistRepository = checklistRepository,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// The whole app: one NavController given to the NavHost.
@Composable
fun CedarPathApp(
    viewModel: TripListViewModel,
    tripRepository: TripRepository,
    checklistRepository: ChecklistRepository,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    AppNavHost(
        navController = navController,
        viewModel = viewModel,
        tripRepository = tripRepository,
        checklistRepository = checklistRepository,
        modifier = modifier
    )
}
