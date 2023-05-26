package id.ac.unpas.composeperkuliahankelompok5.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.composeperkuliahankelompok5.model.Dosen

@Dao
interface DosenDao {
    @Query("SELECT * FROM Dosen ORDER BY nidn ASC")
    fun loadAll(): LiveData<List<Dosen>>

    @Query("SELECT * FROM Dosen ORDER BY nidn ASC")
    suspend fun getList(): List<Dosen>

    @Query("SELECT * FROM Dosen WHERE id = :id")
    suspend fun find(id: String): Dosen?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: Dosen)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<Dosen>)

    @Delete
    fun delete(item: Dosen)

    @Query("DELETE FROM Dosen WHERE id = :id")
    fun delete(id: String)
}