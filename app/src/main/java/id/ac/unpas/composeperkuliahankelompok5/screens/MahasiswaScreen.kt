package id.ac.unpas.composeperkuliahankelompok5.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun MahasiswaScreen() {
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
                startDestination = "pengelolaan-mahasiswa") {
                composable("pengelolaan-mahasiswa") {
                    PengelolaanMahasiswaScreen(navController =
                    navController, snackbarHostState =
                    scaffoldState.snackbarHostState, modifier =
                    Modifier.padding(innerPadding))
                }
                composable("tambah-pengelolaan-mahasiswa") {
                    title.value = "Tambah Pengelolaan Mahasiswa"
                    FormPencatatanMahasiswa(navController =
                    navController, modifier = Modifier.padding(innerPadding))
                }
                composable("edit-pengelolaan-mahasiswa/{id}",
                    listOf(
                        navArgument("id") {
                            type = NavType.StringType
                        }
                    )) { backStackEntry ->
                    title.value = "Edit Pengelolaan Mahasiswa"
                    val id = backStackEntry.arguments?.getString("id") ?: return@composable
                    FormPencatatanMahasiswa(navController =
                    navController, id = id, modifier =
                    Modifier.padding(innerPadding))
                }
            }
        }
    }
}