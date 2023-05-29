package id.ac.unpas.composeperkuliahankelompok5.repositories

import com.benasher44.uuid.uuid4

import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import id.ac.unpas.composeperkuliahankelompok5.model.Dosen
import id.ac.unpas.composeperkuliahankelompok5.networks.DosenApi
import id.ac.unpas.composeperkuliahankelompok5.persistences.DosenDao
import javax.inject.Inject

class DosenRepository @Inject constructor(
    private val api: DosenApi,
    private val dao: DosenDao
) : Repository {
    suspend fun loadItems(
        onSuccess: (List<Dosen>) -> Unit,
        onError: (List<Dosen>, String) -> Unit
    ) {
        val list: List<Dosen> = dao.getList()
        api.all().suspendOnSuccess {
            data.whatIfNotNull {
                it.data?.let { list ->
                    dao.insertAll(list)
                    val items: List<Dosen> = dao.getList()
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
        nidn: String,
        nama: String,
        gelar_depan: String,
        gelar_belakang: String,
        pendidikan: String,
        onSuccess: (Dosen) -> Unit,
        onError: (Dosen?, String) -> Unit
    ) {
        val id = uuid4().toString()
        val item = Dosen(id, nidn, nama, gelar_depan, gelar_belakang, pendidikan)
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
        nidn: String,
        nama: String,
        gelar_depan: String,
        gelar_belakang: String,
        pendidikan: String,
        onSuccess: (Dosen) -> Unit,
        onError: (Dosen?, String) -> Unit
    ) {
        val item = Dosen(id, nidn, nama, gelar_depan, gelar_belakang, pendidikan)
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

    suspend fun find(id: String) : Dosen? {
        return dao.find(id)
        }
}