package id.ac.unpas.composeperkuliahankelompok5.repositories

import com.benasher44.uuid.uuid4

import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import id.ac.unpas.composeperkuliahankelompok5.model.SetoranDosen
import id.ac.unpas.composeperkuliahankelompok5.persistences.SetoranDosenDao
import javax.inject.Inject

class SetoranDosenRepository @Inject constructor(
    private val api: SetoranDosenApi,
    private val dao: SetoranDosenDao
) : Repository {

    suspend fun loadItems(
        onSuccess: (List<SetoranDosen>) -> Unit,
        onError: (List<SetoranDosen>, String) -> Unit
    ) {
        val list: List<SetoranDosen> = dao.getList()
        api.all().suspendOnSuccess {
            data.whatIfNotNull {
                it.data?.let { list ->
                    dao.insertAll(list)
                    val items: List<SetoranDosen> = dao.getList()
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
        gelarDepan: String,
        gelarBelakang: String,
        pendidikan: String,
        onSuccess: (SetoranDosen) -> Unit,
        onError: (SetoranDosen?, String) -> Unit
    ) {
        val id = uuid4().toString()
        val item = SetoranDosen(id, nidn, nama, gelarDepan, gelarBelakang, pendidikan)
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
        gelarDepan: String,
        gelarBelakang: String,
        pendidikan: String,
        onSuccess: (SetoranDosen) -> Unit,
        onError: (SetoranDosen?, String) -> Unit
    ) {
        val item = SetoranDosen(id, nidn, nama, gelarDepan, gelarBelakang, pendidikan)
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

    suspend fun find(id: String) : SetoranDosen? {
        return dao.find(id)
        }
}