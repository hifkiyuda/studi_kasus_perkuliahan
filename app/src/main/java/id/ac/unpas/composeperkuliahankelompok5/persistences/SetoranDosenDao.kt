package id.ac.unpas.composeperkuliahankelompok5.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.composeperkuliahankelompok5.model.SetoranDosen

@Dao
interface SetoranDosenDao {
    @Query("SELECT * FROM SetoranDosen ORDER BY nidn DESC")
    fun loadAll(): LiveData<List<SetoranDosen>>

    @Query("SELECT * FROM SetoranDosen ORDER BY nidn DESC")
    suspend fun getList(): List<SetoranDosen>

    @Query("SELECT * FROM SetoranDosen WHERE id = :id")
    suspend fun find(id: String): SetoranDosen?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: SetoranDosen)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<SetoranDosen>)

    @Delete
    fun delete(item: SetoranDosen)

    @Query("DELETE FROM SetoranDosen WHERE id = :id")
    fun delete(id: String)
}