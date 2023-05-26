package id.ac.unpas.composeperkuliahankelompok5.model

import  androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Mahasiswa(
    @PrimaryKey val id: String,
    val npm: String,
    val nama: String,
    val tanggalLahir: Date,
    val jenisKelamin: JenisKelamin
) {
    enum class JenisKelamin {
        LAKI_LAKI,
        PEREMPUAN
    }
}

