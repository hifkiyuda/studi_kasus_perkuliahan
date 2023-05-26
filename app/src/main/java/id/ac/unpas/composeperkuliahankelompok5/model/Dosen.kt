package id.ac.unpas.composeperkuliahankelompok5.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Dosen (
    @PrimaryKey val id: String,
    val nidn: String,
    val nama: String,
    val gelarDepan: String,
    val gelarBelakang: String,
    val pendidikan: String
)