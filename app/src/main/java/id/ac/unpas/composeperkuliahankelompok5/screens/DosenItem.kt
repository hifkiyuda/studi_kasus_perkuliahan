package id.ac.unpas.composeperkuliahankelompok5.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.message
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import com.vanpra.composematerialdialogs.title
import id.ac.unpas.composeperkuliahankelompok5.model.Dosen

@Composable
fun DosenItem(item: Dosen, navController: NavHostController, onDelete: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val subMenus = listOf("Edit", "Delete")
    val confirmationDialogState = rememberMaterialDialogState()

    Column(modifier = Modifier.fillMaxWidth()) {
        Card(
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ){
            Row(modifier = Modifier.padding(10.dp).fillMaxWidth()) {
                Column(modifier = Modifier.weight(10f)) {
                    Row(modifier = Modifier.padding(4.dp).fillMaxWidth()) {
                        Column(Modifier.weight(2f)) {
                            Text(text = "NIDN", fontSize = 15.sp)
                        }
                        Column(Modifier.weight(6f)) {
                            Text(text = ": " + item.nidn, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                    Row(modifier = Modifier.padding(4.dp).fillMaxWidth()) {
                        Column(Modifier.weight(2f)) {
                            Text(text = "Nama", fontSize = 15.sp)
                        }
                        Column(Modifier.weight(6f)) {
                            Text(text = ": " + item.nama, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                    Row(modifier = Modifier.padding(4.dp).fillMaxWidth()) {
                        Column(Modifier.weight(2f)) {
                            Text(text = "Gelar Depan", fontSize = 15.sp)
                        }
                        Column(Modifier.weight(6f)) {
                            Text(text = ": " + item.gelar_depan, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                    Row(modifier = Modifier.padding(4.dp).fillMaxWidth()) {
                        Column(Modifier.weight(2f)) {
                            Text(text = "Gelar Belakang", fontSize = 15.sp)
                        }
                        Column(Modifier.weight(6f)) {
                            Text(text = ": " + item.gelar_belakang, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                    Row(modifier = Modifier.padding(4.dp).fillMaxWidth()) {
                        Column(Modifier.weight(2f)) {
                            Text(text = "Pendidikan", fontSize = 15.sp)
                        }
                        Column(Modifier.weight(6f)) {
                            Text(text = ": " + item.pendidikan, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
                Icon(
                    Icons.Default.MoreVert,
                    modifier = Modifier
                        .height(40.dp)
                        .width(40.dp)
                        .padding(8.dp)
                        .weight(1f, true)
                        .clickable {
                            expanded = true
                        },
                    contentDescription = null
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                offset = DpOffset(x = (250).dp, y = (-150).dp)
            ) {
                subMenus.forEachIndexed { _, s ->
                    DropdownMenuItem(onClick = {
                        expanded = false
                        when (s) {
                            "Edit" -> {
                                navController.navigate("edit-pengelolaan-dosen/${item.id}")
                            }
                            "Delete" -> {
                                confirmationDialogState.show()
                            }
                        }
                    }) {
                        Text(text = s)
                    }
                }
            }
        }
    }
    MaterialDialog(dialogState = confirmationDialogState,
        buttons = {
            positiveButton("Ya", onClick = {
                onDelete(item.id)
            })
            negativeButton("Tidak")
        }) {
        title(text = "Konfirmasi")
        message(text = "Apakah anda yakin ingin menghapus data ini?")
    }
}