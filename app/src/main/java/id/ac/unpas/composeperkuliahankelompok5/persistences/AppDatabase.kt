package id.ac.unpas.composeperkuliahankelompok5.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.composeperkuliahankelompok5.model.Dosen
import id.ac.unpas.composeperkuliahankelompok5.model.Mahasiswa
import id.ac.unpas.composeperkuliahankelompok5.model.Matakuliah

@Database(entities = [Dosen::class, Mahasiswa::class, Matakuliah::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dosenDao(): DosenDao
    abstract fun mahasiswaDao(): MahasiswaDao
    abstract fun matakuliahDao(): MatakuliahDao
}