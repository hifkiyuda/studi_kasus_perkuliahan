package id.ac.unpas.composeperkuliahankelompok5.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import id.ac.unpas.composeperkuliahankelompok5.model.Dosen
import id.ac.unpas.composeperkuliahankelompok5.ui.theme.Purple700
import id.ac.unpas.composeperkuliahankelompok5.ui.theme.Teal200
import kotlinx.coroutines.launch

@Composable
fun FormPencatatanDosen (navController: NavHostController, id: String? = null, modifier: Modifier = Modifier) {
    val isLoading = remember { mutableStateOf(false) }
    val buttonLabel = if (isLoading.value) "Mohon tunggu..." else "Simpan"
    val viewModel = hiltViewModel<PengelolaanDosenViewModel>()
    val nidn = remember { mutableStateOf(TextFieldValue("")) }
    val nama = remember { mutableStateOf(TextFieldValue("")) }
    val gelarDepan = remember { mutableStateOf(TextFieldValue("")) }
    val gelarBelakang = remember { mutableStateOf(TextFieldValue("")) }
    val pendidikan = remember { mutableStateOf(TextFieldValue("")) }
//    val pendidikanOptions = Dosen.Pendidikan.values()
//    val selectedPendidikan = remember { mutableStateOf(pendidikanOptions[0]) }
//    val expanded = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        OutlinedTextField(
            label = { Text(text = "NIDN") },
            value = nidn.value,
            onValueChange = {
                nidn.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            placeholder = { Text(text = "Masukkan NIDN") }
        )
        OutlinedTextField(
            label = { Text(text = "Nama") },
            value = nama.value,
            onValueChange = {
                nama.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "Masukkan nama") }
        )
        OutlinedTextField(
            label = { Text(text = "Gelar Depan") },
            value = gelarDepan.value,
            onValueChange = {
                gelarDepan.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "Contoh: Dr") }
        )
        OutlinedTextField(
            label = { Text(text = "Gelar Belakang") },
            value = gelarBelakang.value,
            onValueChange = {
                gelarBelakang.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "Contoh: MT") }
        )
        OutlinedTextField(
            label = { Text(text = "Pendidikan") },
            value = pendidikan.value,
            onValueChange = {
                pendidikan.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "Contoh: S2") }
        )
//        OutlinedTextField(
//            label = { Text(text = "Pendidikan") },
//            value = selectedPendidikan.value.toString(),
//            onValueChange = { },
//            modifier = Modifier.fillMaxWidth().clickable { expanded.value = true },
//            readOnly = true
//        )
//        DropdownMenu(
//            expanded = expanded.value,
//            onDismissRequest = { expanded.value = false },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            pendidikanOptions.forEach { option ->
//                DropdownMenuItem(onClick = {
//                    selectedPendidikan.value = option
//                    expanded.value = false
//                }) {
//                    Text(text = option.toString())
//                }
//            }
//        }
        val simpanButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Purple700,
            contentColor = Teal200
        )
        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = Purple700
        )
        Row (modifier = Modifier.padding(4.dp).fillMaxWidth()) {
            Button(modifier = Modifier.weight(5f), onClick = {
                if (id == null) {
                    scope.launch {
                        viewModel.insert(nidn.value.text, nama.value.text, gelarDepan.value.text, gelarBelakang.value.text, pendidikan.value.text)

                    }
                } else {
                    scope.launch {
                        viewModel.update(id, nidn.value.text, nama.value.text, gelarDepan.value.text, gelarBelakang.value.text, pendidikan.value.text)
                    }
                }
                navController.navigate("pengelolaan-dosen")
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
                pendidikan.value = TextFieldValue("")
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
                    pendidikan.value = TextFieldValue(dosen.pendidikan)
                }
            }
        }
    }
}