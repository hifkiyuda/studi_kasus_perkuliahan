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
import id.ac.unpas.composeperkuliahankelompok5.ui.theme.LightRed
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val title = remember { mutableStateOf("") }
    val appBarHorizontalPadding = 4.dp
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = LightRed,
                elevation = 0.dp,
                modifier= Modifier.fillMaxWidth()) {
                //TopAppBar Content
                Box(Modifier.height(32.dp)) {
                    Row(
                        Modifier
                            .fillMaxHeight()
                            .width(72.dp - appBarHorizontalPadding), verticalAlignment = Alignment.CenterVertically) {
                        CompositionLocalProvider(
                            LocalContentAlpha provides
                                    ContentAlpha.high,
                        ) {
                            IconButton(
                                onClick = { },
                                enabled = true,
                            ) {
                                Icon(
                                    Icons.Filled.Menu, null,
                                    tint = Color.White)
                            }
                        }
                    }
                    Row(
                        Modifier.fillMaxSize(),
                        verticalAlignment =
                        Alignment.CenterVertically) {
                        ProvideTextStyle(value =
                        MaterialTheme.typography.h6) {
                            CompositionLocalProvider(
                                LocalContentAlpha provides
                                        ContentAlpha.high,
                            ){
                                Text(
                                    modifier =
                                    Modifier.fillMaxWidth(),
                                    textAlign =
                                    TextAlign.Center,
                                    color = Color.White,
                                    maxLines = 1,
                                    text = title.value
                                )
                            }
                        }
                    }
                }
            }
        },
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(it) { data ->
                Snackbar(
                    actionColor = Color.Green,
                    contentColor = Color.White,
                    snackbarData = data
                )
            }
        },
        drawerContent = {
            DrawerContent { route ->
                navController.navigate(route)
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            }
        },
        bottomBar = {
            BottomNavigationComposable(title.value, onClick =
            { tab ->
                navController.navigate(tab.route)
            })
        },
    )
    { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavHost(navController = navController,
                startDestination = "home") {
                composable("pengelolaan-dosen") {
                    title.value = "Pengelolaan Perkuliahan"
                    PengelolaanDosenScreen(navController =
                    navController, snackbarHostState =
                    scaffoldState.snackbarHostState )
                }
                composable("tambah-pengelolaan-dosen") {
                    title.value = "Tambah Pengelolaan Dosen"
                    FormPencatatanDosen(navController =
                    navController, modifier = Modifier.padding(innerPadding))
                }
                composable("edit-pengelolaan-dosen/{id}",
                    listOf(
                        navArgument("id") {
                            type = NavType.StringType
                        }
                    )) { backStackEntry ->
                    title.value = "Edit Pengelolaan Dosen"
                    val id = backStackEntry.arguments?.getString("id") ?: return@composable
                    FormPencatatanDosen(navController =
                    navController, id = id, modifier =
                    Modifier.padding(innerPadding))
                }

                composable("pengelolaan-mahasiswa") {
                    title.value = "Pengelolaan Perkuliahan"
                    PengelolaanMahasiswaScreen(navController =
                    navController, snackbarHostState =
                    scaffoldState.snackbarHostState)
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

                composable("pengelolaan-matakuliah") {
                    title.value = "Pengelolaan Perkuliahan"
                    PengelolaanMatakuliahScreen(navController =
                    navController, snackbarHostState =
                    scaffoldState.snackbarHostState)
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

                composable("home") {
                    title.value = "Pengelolaan Perkuliahan"
                    HomeScreen()
                }
            }
        }
    }
}