package id.ac.unpas.composeperkuliahankelompok5.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(14.dp).fillMaxWidth()) {
            Column(modifier = Modifier.weight(10f)){
                Text(text = "Kelompok 5:", fontSize = 16.sp)
                Text(text = "203040071 - Hifki Yuda Pratama", fontSize = 16.sp)
                Text(text = "203040068 - Muhammad Fazril Fuady Hermawan", fontSize = 16.sp)
                Text(text = "203040078 - Siti Komalasari", fontSize = 16.sp)
                Text(text = "203040070 - Priyandi Zembar Azizi", fontSize = 16.sp)
                Text(text = "203040087 - Hamzah Hadi Permana", fontSize = 16.sp)
            }
        }
    }
}