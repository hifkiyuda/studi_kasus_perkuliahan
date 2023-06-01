package id.ac.unpas.composeperkuliahankelompok5.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import id.ac.unpas.composeperkuliahankelompok5.ui.theme.Purple700
import id.ac.unpas.composeperkuliahankelompok5.ui.theme.Teal200
import kotlinx.coroutines.launch
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity

@Composable
fun FormPencatatanDosen (navController: NavHostController, id: String? = null, modifier: Modifier = Modifier) {
    val isLoading = remember { mutableStateOf(false) }
    val buttonLabel = if (isLoading.value) "Mohon tunggu..." else "Simpan"
    val viewModel = hiltViewModel<PengelolaanDosenViewModel>()
    val nidn = remember { mutableStateOf(TextFieldValue("")) }
    val nama = remember { mutableStateOf(TextFieldValue("")) }
    val gelarDepan = remember { mutableStateOf(TextFieldValue("")) }
    val gelarBelakang = remember { mutableStateOf(TextFieldValue("")) }
    val pendidikan = listOf("S2","S3")
    var selectedPendidikan by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero)}
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        OutlinedTextField(
            label = { Text(text = "NIDN") },
            value = nidn.value,
            onValueChange = {
                nidn.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "Masukkan NIDN") }
        )
        OutlinedTextField(
            label = { Text(text = "Nama") },
            value = nama.value,
            onValueChange = {
                nama.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "Masukkan nama") }
        )
        OutlinedTextField(
            label = { Text(text = "Gelar Depan") },
            value = gelarDepan.value,
            onValueChange = {
                gelarDepan.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "Contoh: Dr") }
        )
        OutlinedTextField(
            label = { Text(text = "Gelar Belakang") },
            value = gelarBelakang.value,
            onValueChange = {
                gelarBelakang.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "Contoh: MT") }
        )
        Column(modifier = Modifier.padding(4.dp, bottom = 10.dp).fillMaxWidth()) {
            OutlinedTextField(
                value = selectedPendidikan,
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        textFieldSize = coordinates.size.toSize()
                    },
                label = {Text("Pendidikan")},
                trailingIcon = {
                    Icon(icon,"Silakan pilih pendidikan",
                        Modifier.clickable { expanded = !expanded })
                }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.width(with(LocalDensity.current){textFieldSize.width.toDp()})
            ) {
                pendidikan.forEach { label ->
                    DropdownMenuItem(onClick = {
                        selectedPendidikan = label
                        expanded = false
                    }) {
                        Text(text = label)
                    }
                }
            }
        }
        val simpanButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Purple700,
            contentColor = Teal200
        )
        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = Purple700
        )
        Row (modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()) {
            Button(modifier = Modifier.weight(5f), onClick = {
                if (nidn.value.text.isNotBlank()
                    && nama.value.text.isNotBlank()
                    && gelarDepan.value.text.isNotBlank()
                    && gelarBelakang.value.text.isNotBlank()
                    && selectedPendidikan.isNotBlank()) {
                    if (id == null) {
                        scope.launch {
                            viewModel.insert(nidn.value.text, nama.value.text, gelarDepan.value.text, gelarBelakang.value.text, selectedPendidikan)
                        }
                    } else {
                        scope.launch {
                            viewModel.update(id, nidn.value.text, nama.value.text, gelarDepan.value.text, gelarBelakang.value.text, selectedPendidikan)
                        }
                    }
                    navController.navigate("pengelolaan-dosen")
                } else {
                    Toast.makeText(context, "Silakan isi kolom yang masih kosong", Toast.LENGTH_LONG).show()
                }
            }, colors = simpanButtonColors) {
                Text(
                    text = buttonLabel,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
            Button(modifier = Modifier.weight(5f), onClick = {
                nidn.value = TextFieldValue("")
                nama.value = TextFieldValue("")
                gelarDepan.value = TextFieldValue("")
                gelarBelakang.value = TextFieldValue("")
                selectedPendidikan = ""
            }, colors = resetButtonColors) {
                Text(
                    text = "Reset",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
        }
    }

    viewModel.isLoading.observe(LocalLifecycleOwner.current) {
        isLoading.value = it
    }

    if (id != null) {
        LaunchedEffect(scope) {
            viewModel.loadItem(id) { dosen ->
                dosen?.let {
                    nidn.value = TextFieldValue(dosen.nidn)
                    nama.value = TextFieldValue(dosen.nama)
                    gelarDepan.value = TextFieldValue(dosen.gelar_depan)
                    gelarBelakang.value = TextFieldValue(dosen.gelar_belakang)
                    selectedPendidikan =  dosen.pendidikan
                }
            }
        }
    }
}