package id.ac.unpas.composeperkuliahankelompok5.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.semantics.Role.Companion.Checkbox
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import id.ac.unpas.composeperkuliahankelompok5.ui.theme.Purple700
import id.ac.unpas.composeperkuliahankelompok5.ui.theme.Teal200
import kotlinx.coroutines.launch

@Composable
fun FormPencatatanMatakuliah (navController: NavHostController, id: String? = null, modifier: Modifier = Modifier) {
    val isLoading = remember { mutableStateOf(false) }
    val buttonLabel = if (isLoading.value) "Mohon tunggu..." else "Simpan"
    val viewModel = hiltViewModel<PengelolaanMatakuliahViewModel>()
    val kode = remember { mutableStateOf(TextFieldValue("")) }
    val nama = remember { mutableStateOf(TextFieldValue("")) }
    val sks = remember { mutableStateOf(0.toByte()) }
    val praktikum = remember { mutableStateOf(false) }
    val praktikumInt = praktikum.value.toInt()
    val deskripsi = remember { mutableStateOf(TextFieldValue("")) }
    val scope = rememberCoroutineScope()

    Column( modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        OutlinedTextField(
            label = { Text(text = "Kode") },
            value = kode.value,
            onValueChange = {
                kode.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            placeholder = { Text(text = "Masukkan kode matakuliah") }
        )
        OutlinedTextField(
            label = { Text(text = "Nama") },
            value = nama.value,
            onValueChange = {
                nama.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "Masukkan nama") }
        )
        OutlinedTextField(
            label = { Text(text = "SKS") },
            value = sks.value.toString(),
            onValueChange = { newValue ->
                val parsedValue = newValue.toByteOrNull()
                sks.value = parsedValue ?: 0.toByte()
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
        OutlinedTextField(
            label = { Text(text = "Deskripsi") },
            value = deskripsi.value,
            onValueChange = {
                deskripsi.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "Masukkan deskripsi") }
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(4.dp)
        ) {
            Text(text = "Praktikum", fontSize = 16.sp)
            Checkbox(
                checked = praktikum.value,
                onCheckedChange = {
                    praktikum.value = it
                },
                modifier = Modifier.padding(4.dp)
            )
        }
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
                        viewModel.insert(kode.value.text, nama.value.text, sks.value, praktikumInt, deskripsi.value.text)

                    }
                } else {
                    scope.launch {
                        viewModel.update(id, kode.value.text, nama.value.text, sks.value, praktikumInt, deskripsi.value.text)
                    }
                }
                navController.navigate("pengelolaan-matakuliah")
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
                kode.value = TextFieldValue("")
                nama.value = TextFieldValue("")
                sks.value = 0.toByte()
                praktikum.value = false
                deskripsi.value = TextFieldValue("")
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
            viewModel.loadItem(id) { matakuliah ->
                matakuliah?.let {
                    kode.value = TextFieldValue(matakuliah.kode)
                    nama.value = TextFieldValue(matakuliah.nama)
                    sks.value = matakuliah.sks
                    praktikum.value = false
                    deskripsi.value = TextFieldValue(matakuliah.deskripsi)
                }
            }
        }
    }
}

fun Boolean.toInt(): Int {
    return if (this) 1 else 0
}