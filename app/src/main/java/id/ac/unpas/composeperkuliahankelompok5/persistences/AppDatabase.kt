package id.ac.unpas.composeperkuliahankelompok5.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.composeperkuliahankelompok5.model.SetoranDosen
import id.ac.unpas.composeperkuliahankelompok5.model.Mahasiswa
import id.ac.unpas.composeperkuliahankelompok5.model.Matakuliah

@Database(entities = [SetoranDosen::class], version = 1)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun setoranDosenDao(): SetoranDosenDao
        abstract fun mahasiswaDao(): MahasiswaDao
        abstract fun matakuliahDao(): MatakuliahDao
    }
