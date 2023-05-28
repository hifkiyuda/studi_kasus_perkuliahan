package id.ac.unpas.composeperkuliahankelompok5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import id.ac.unpas.composeperkuliahankelompok5.screens.PengelolaanMatakuliahScreen
import id.ac.unpas.composeperkuliahankelompok5.ui.theme.ComposePerkuliahanKelompok5Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePerkuliahanKelompok5Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PengelolaanMatakuliahScreen()
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePerkuliahanKelompok5Theme {
        PengelolaanMatakuliahScreen()
    }
}