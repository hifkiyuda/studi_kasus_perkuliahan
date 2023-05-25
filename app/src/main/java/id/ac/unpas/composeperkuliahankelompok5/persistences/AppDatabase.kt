package id.ac.unpas.composeperkuliahankelompok5.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.composeperkuliahankelompok5.model.SetoranDosen

@Database(entities = [SetoranDosen::class], version = 1)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun setoranDosenDao(): SetoranDosenDao
    }