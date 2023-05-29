package id.ac.unpas.composeperkuliahankelompok5.screens

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import id.ac.unpas.composeperkuliahankelompok5.R

enum class Menu (
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String
) {
    PENGELOLAAN_DOSEN(
        R.string.pengelolaan_dosen,
        Icons.Default.List, "pengelolaan-dosen"),
    PENGELOLAAN_MAHASISWA(
        R.string.pengelolaan_mahasiswa,
        Icons.Default.List, "pengelolaan-mahasiswa"),
    PENGELOLAAN_MATAKULIAH(
        R.string.pengelolaan_matakuliah,
        Icons.Default.List, "pengelolaan-matakuliah");
    companion object {
        fun getTabFromResource(@StringRes resource: Int) : Menu
        {
            return when (resource) {
                R.string.pengelolaan_dosen -> PENGELOLAAN_DOSEN
                R.string.pengelolaan_mahasiswa -> PENGELOLAAN_MAHASISWA
                else -> PENGELOLAAN_MATAKULIAH
            }
        }
    }
}