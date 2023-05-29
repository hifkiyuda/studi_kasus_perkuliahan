package id.ac.unpas.composeperkuliahankelompok5.model

import  androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Mahasiswa(
    @PrimaryKey val id: String,
    val npm: String,
    val nama: String,
    val tanggal_lahir: String,
    val jenis_kelamin: String
) {
//    enum class JenisKelamin {
//        LAKI_LAKI,
//        PEREMPUAN
//    }
}
