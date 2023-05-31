package id.ac.unpas.composeperkuliahankelompok5.repositories

import com.benasher44.uuid.uuid4
import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import id.ac.unpas.composeperkuliahankelompok5.model.Matakuliah
import id.ac.unpas.composeperkuliahankelompok5.networks.MatakuliahApi
import id.ac.unpas.composeperkuliahankelompok5.persistences.MatakuliahDao
import javax.inject.Inject

class MatakuliahRepository @Inject constructor(
    private val api: MatakuliahApi,
    private val dao: MatakuliahDao
) : Repository {
    suspend fun loadItems(
        onSuccess: (List<Matakuliah>) -> Unit,
        onError: (List<Matakuliah>, String) -> Unit
    ) {
        val list: List<Matakuliah> = dao.getList()
        api.all().suspendOnSuccess {
            data.whatIfNotNull {
                it.data?.let { list ->
                    dao.insertAll(list)
                    val items: List<Matakuliah> = dao.getList()
                    onSuccess(items)
                }
            }
        }
            .suspendOnError {
                onError(list, message())
            }
            .suspendOnException {
                onError(list, message())
            }
    }

    suspend fun insert(
        kode: String,
        nama: String,
        sks: Byte,
        praktikum: Int,
        deskripsi: String,
        onSuccess: (Matakuliah) -> Unit,
        onError: (Matakuliah?, String) -> Unit
    ) {
        val id = uuid4().toString()
        val item = Matakuliah(id, kode, nama, sks, praktikum, deskripsi)
        dao.insertAll(item)
        api.insert(item)
            .suspendOnSuccess {
                onSuccess(item)
            }
            .suspendOnError {
                onError(item, message())
            }
            .suspendOnException {
                onError(item, message())
            }
    }

    suspend fun update(
        id: String,
        kode: String,
        nama: String,
        sks: Byte,
        praktikum: Int,
        deskripsi: String,
        onSuccess: (Matakuliah) -> Unit,
        onError: (Matakuliah?, String) -> Unit
    ) {
        val item = Matakuliah(id, kode, nama, sks, praktikum, deskripsi)
        dao.insertAll(item)
        api.update(id, item)
            .suspendOnSuccess {
                onSuccess(item)
            }
            .suspendOnError {
                onError(item, message())
            }
            .suspendOnException {
                onError(item, message())
            }
    }

    suspend fun delete(id: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        dao.delete(id)
        api.delete(id)
            .suspendOnSuccess {
                data.whatIfNotNull {
                    onSuccess()
                }
            }
            .suspendOnError {
                onError(message())
            }
            .suspendOnException {
                onError(message())
            }
    }

    suspend fun find(id: String) : Matakuliah? {
        return dao.find(id)
    }
}