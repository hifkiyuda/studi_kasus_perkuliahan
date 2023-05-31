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
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import id.ac.unpas.composeperkuliahankelompok5.model.Mahasiswa
import id.ac.unpas.composeperkuliahankelompok5.ui.theme.Purple700
import id.ac.unpas.composeperkuliahankelompok5.ui.theme.Teal200
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter

@Composable
fun FormPencatatanMahasiswa (navController: NavHostController, id: String? = null, modifier: Modifier = Modifier) {
    val isLoading = remember { mutableStateOf(false) }
    val buttonLabel = if (isLoading.value) "Mohon tunggu..." else "Simpan"
    val viewModel = hiltViewModel<PengelolaanMahasiswaViewModel>()
    val npm = remember { mutableStateOf(TextFieldValue("")) }
    val nama = remember { mutableStateOf(TextFieldValue("")) }
    val tanggalLahir = remember { mutableStateOf(TextFieldValue("")) }
    val jenisKelamin = remember { mutableStateOf(TextFieldValue("")) }
    val scope = rememberCoroutineScope()
    val tanggalDialogState = rememberMaterialDialogState()

    Column( modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        OutlinedTextField(
            label = { Text(text = "NPM") },
            value = npm.value,
            onValueChange = {
                npm.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            placeholder = { Text(text = "Masukkan NPM") }
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
            label = { Text(text = "Tanggal Lahir") },
            value = tanggalLahir.value,
            onValueChange = {
                tanggalLahir.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .clickable {
                    tanggalDialogState.show()
                },
            placeholder = { Text(text = "yyyy-mm-dd") },
            enabled = false
        )
        OutlinedTextField(
            label = { Text(text = "Jenis Kelamin") },
            value = jenisKelamin.value,
            onValueChange = {
                jenisKelamin.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "Laki-laki/Perempuan") }
        )
        val simpanButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Purple700,
            contentColor = Teal200
        )
        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = Purple700
        )
        Row(modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()) {
            Button(modifier = Modifier.weight(5f), onClick = {
                if (id == null) {
                    scope.launch {
                        viewModel.insert(npm.value.text, nama.value.text, tanggalLahir.value.text, jenisKelamin.value.text)

                    }
                } else {
                    scope.launch {
                        viewModel.update(id, npm.value.text, nama.value.text, tanggalLahir.value.text, jenisKelamin.value.text)
                    }
                }
                navController.navigate("pengelolaan-mahasiswa")
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
                npm.value = TextFieldValue("")
                nama.value = TextFieldValue("")
                tanggalLahir.value = TextFieldValue("")
                jenisKelamin.value = TextFieldValue("")
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
            viewModel.loadItem(id) { mahasiswa ->
                mahasiswa?.let {
                    npm.value = TextFieldValue(mahasiswa.npm)
                    nama.value = TextFieldValue(mahasiswa.nama)
                    tanggalLahir.value = TextFieldValue(mahasiswa.tanggal_lahir)
                    jenisKelamin.value = TextFieldValue(mahasiswa.jenis_kelamin)
                }
            }
        }
    }
    MaterialDialog(dialogState = tanggalDialogState, buttons = {
        positiveButton("OK")
        negativeButton("Batal")
    }) {
        datepicker { date ->
            tanggalLahir.value =
                TextFieldValue(date.format(DateTimeFormatter.ISO_LOCAL_DATE))
        }
    }
}