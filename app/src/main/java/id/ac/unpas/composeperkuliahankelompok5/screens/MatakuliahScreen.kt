package id.ac.unpas.composeperkuliahankelompok5.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import id.ac.unpas.composeperkuliahankelompok5.ui.theme.Purple700
import kotlinx.coroutines.launch

@Composable
fun MatakuliahScreen() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val title = remember { mutableStateOf("") }
    Scaffold()
    { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavHost(navController = navController,
                startDestination = "pengelolaan-matakuliah") {
                composable("pengelolaan-dosen") {
                    DosenScreen()
                }
                composable("pengelolaan-mahasiswa") {
                    MahasiswaScreen()
                }
                composable("pengelolaan-matakuliah") {
                    title.value = "Pengelolaan Perkuliahan"
                    PengelolaanMatakuliahScreen(navController =
                    navController, snackbarHostState =
                    scaffoldState.snackbarHostState, modifier =
                    Modifier.padding(innerPadding))
                }
                composable("tambah-pengelolaan-matakuliah") {
                    title.value = "Tambah Pengelolaan Matakuliah"
                    FormPencatatanMatakuliah(navController =
                    navController, modifier = Modifier.padding(innerPadding))
                }
                composable("edit-pengelolaan-matakuliah/{id}",
                    listOf(
                        navArgument("id") {
                            type = NavType.StringType
                        }
                    )) { backStackEntry ->
                    title.value = "Edit Pengelolaan Matakuliah"
                    val id = backStackEntry.arguments?.getString("id") ?: return@composable
                    FormPencatatanMatakuliah(navController =
                    navController, id = id, modifier =
                    Modifier.padding(innerPadding))
                }
            }
        }
    }
}