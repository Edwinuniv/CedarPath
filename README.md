# CedarPath

CedarPath is a hiking trip planner and trail journal for Android, written in Kotlin with Jetpack Compose.
It is for hikers who want to plan their upcoming trips and keep a packing checklist for each one.

Android Development final project, topic 8: "Hiking Trip Planner and Trail Journal".
Team: Edwin Mouawad and Joe Boukaram.

## Midterm feature: create a trip and tick off packing items

- **Create a trip** (Edwin): enter the route name, the date and the difficulty (Easy, Medium or Hard).
- **Upcoming trips list** (Edwin): every trip that is not completed yet, the nearest date first.
- **Trip detail and checklist** (Joe): open a trip, add packing items and tick them off.
- Everything is saved in a local Room database, so trips and ticked items are still there after the app restarts.

## How it is built

- **UI:** Jetpack Compose with Material 3 (`ui/trips`, `ui/detail`)
- **Navigation:** Navigation Compose with three routes: `trips`, `trip-form` and `trip/{tripId}` (`ui/AppNavHost.kt`)
- **Architecture:** MVVM. Each screen gets its data from a ViewModel (`TripListViewModel`, `TripDetailViewModel`),
  which reads it from a repository (`TripRepository`, `ChecklistRepository`).
- **Local storage:** Room with two tables, `trips` and `checklist_items` (linked by `tripId`) (`data/`)

```
Compose screen  ->  ViewModel  ->  Repository  ->  DAO  ->  Room database
```

## How to run the app

1. Install [Android Studio](https://developer.android.com/studio) (a recent version).
2. Clone the repository:
   ```
   git clone https://github.com/Edwinuniv/CedarPath.git
   ```
3. Open the `CedarPath` folder in Android Studio and wait for the Gradle sync to finish.
   The project needs **JDK 17 or 21** for Gradle
   (Settings > Build, Execution, Deployment > Build Tools > Gradle > Gradle JDK).
4. Connect an Android phone with USB debugging turned on, or start an emulator (Android 8.0 / API 26 or newer).
5. Press **Run** (the green play button) to install and open CedarPath.

From the command line you can also build the app with:
```
./gradlew :app:assembleDebug
```

If an older test version of the app was installed and it crashes on start, uninstall it first
(the database tables changed during development).

## Try it

1. Tap **Add trip**, type a route name and a date (for example `2026-12-05`), choose a difficulty, tap **Save trip**.
2. The trip appears in **Upcoming trips**. Tap **Open**.
3. Type a packing item (for example `Water bottle`) and tap **Add**. Tick it.
4. Close the app and open it again: the trip and the ticked item are still there.

## Next steps (final project)

- Mark trips as completed and show them in a trail journal
- Edit and delete trips and checklist items
- Weather for the trip date from an online API, with loading and error states
- A reminder notification before a trip
