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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import java.text.SimpleDateFormat
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import id.ac.unpas.composeperkuliahankelompok5.model.Mahasiswa
import id.ac.unpas.composeperkuliahankelompok5.model.Mahasiswa.JenisKelamin
import kotlinx.coroutines.launch

@Composable
fun PengelolaanMahasiswaScreen(snackbarHostState: SnackbarHostState, navController : NavHostController, modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val viewModel = hiltViewModel<PengelolaanMahasiswaViewModel>()
    val items: List<Mahasiswa> by viewModel.list.observeAsState(initial = listOf())

    Column(modifier = modifier.fillMaxWidth()) {
        Button(onClick = {
            navController.navigate("tambah-pengelolaan-mahasiswa")
        }) {
            Text(text = "Tambah")
        }
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(items = items, itemContent = { item ->
                Row(modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth().clickable {
                        navController.navigate("edit-pengelolaan-mahasiswa/${item.id}")
                    }) {
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "NPM", fontSize = 14.sp)
                        Text(
                            text = item.npm, fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Nama", fontSize = 14.sp)
                        Text(
                            text = item.nama, fontSize = 16.sp, fontWeight =
                            FontWeight.Bold
                        )
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Tanggal Lahir", fontSize = 14.sp)
                        Text(
                            text = SimpleDateFormat("dd MMMM yyyy").format(item.tanggalLahir), fontSize = 16.sp, fontWeight =
                            FontWeight.Bold
                        )
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Jenis Kelamin", fontSize = 14.sp)
                        Text(
                            text = when (item.jenisKelamin) {
                                JenisKelamin.LAKI_LAKI -> "Laki-laki"
                                JenisKelamin.PEREMPUAN -> "Perempuan"
                            },
                            fontSize = 16.sp, fontWeight =
                            FontWeight.Bold
                        )
                    }
                }
                Divider(modifier = Modifier.fillMaxWidth())
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
            snackbarHostState.showSnackbar(
                it,
                actionLabel = "Tutup",
                duration = SnackbarDuration.Long
            )
        }
    }
}