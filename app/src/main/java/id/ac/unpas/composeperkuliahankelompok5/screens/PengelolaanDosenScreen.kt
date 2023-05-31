package id.ac.unpas.composeperkuliahankelompok5.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import id.ac.unpas.composeperkuliahankelompok5.model.Dosen
import kotlinx.coroutines.launch

@Composable
fun PengelolaanDosenScreen(snackbarHostState: SnackbarHostState, navController : NavHostController, modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val viewModel = hiltViewModel<PengelolaanDosenViewModel>()
    val items: List<Dosen> by viewModel.list.observeAsState(initial = listOf())

    Column(modifier = modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp).fillMaxWidth()) {
            Column(modifier = Modifier.weight(10f)){
                Text(text = "Halaman Dosen", fontSize = 16.sp)
            }
            Column(modifier = Modifier.weight(4f)){
                Button(onClick = {
                    navController.navigate("tambah-pengelolaan-dosen")
                }) {
                    Text(text = "Tambah")
                }
            }
        }
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(items = items, itemContent = { item ->
                DosenItem(item = item, navController = navController) {
                    scope.launch {
                        viewModel.delete(it)
                    }
                }
            })
        }
    }

    LaunchedEffect(scope) {
        viewModel.loadItems()
    }

    viewModel.success.observe(LocalLifecycleOwner.current) {
        if (it) {
            scope.launch {
                viewModel.loadItems()
            }
        }
    }

    viewModel.toast.observe(LocalLifecycleOwner.current) {
        scope.launch {
            snackbarHostState.showSnackbar(it, actionLabel = "Tutup", duration = SnackbarDuration.Long)
            }
        }
}