package id.ac.unpas.composeperkuliahankelompok5.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ac.unpas.composeperkuliahankelompok5.model.Matakuliah
import id.ac.unpas.composeperkuliahankelompok5.repositories.MatakuliahRepository
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PengelolaanMatakuliahViewModel @Inject constructor(private val matakuliahRepository: MatakuliahRepository) : ViewModel()
{
    private val _isLoading: MutableLiveData<Boolean> =
        MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading
    private val _success: MutableLiveData<Boolean> =
        MutableLiveData(false)
    val success: LiveData<Boolean> get() = _success
    private val _toast: MutableLiveData<String> =
        MutableLiveData()
    val toast: LiveData<String> get() = _toast
    private val _list: MutableLiveData<List<Matakuliah>> =
        MutableLiveData()
    val list: LiveData<List<Matakuliah>> get() = _list
    suspend fun loadItems()
    {
        _isLoading.postValue(true)
        matakuliahRepository.loadItems(onSuccess = {
            _isLoading.postValue(false)
            _list.postValue(it)
        }, onError = { list, message ->
            _toast.postValue(message)
            _isLoading.postValue(false)
            _list.postValue(list)
        })
    }
    suspend fun insert(kode: String,
                       nama: String,
                       sks: Byte,
                       praktikum: Int,
                       deskripsi: String){
        _isLoading.postValue(true)
        matakuliahRepository.insert(kode, nama, sks, praktikum, deskripsi,
            onError = { item, message ->
                _toast.postValue(message)
                _isLoading.postValue(false)
            }, onSuccess = {
                _isLoading.postValue(false)
                _success.postValue(true)
            })
    }
    suspend fun loadItem(id: String, onSuccess: (Matakuliah?) -> Unit) {
        val item = matakuliahRepository.find(id)
        onSuccess(item)
    }
    suspend fun update(id: String,
                       kode: String,
                       nama: String,
                       sks: Byte,
                       praktikum: Int,
                       deskripsi: String){
        _isLoading.postValue(true)
        matakuliahRepository.update(id, kode, nama, sks, praktikum, deskripsi,
            onError = { item, message ->
                _toast.postValue(message)
                _isLoading.postValue(false)
            }, onSuccess = {
                _isLoading.postValue(false)
                _success.postValue(true)
            })
    }
}