package id.ac.unpas.composeperkuliahankelompok5.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ac.unpas.functionalcompose.model.Mahasiswa
import id.ac.unpas.functionalcompose.repositories.Mahasiswa
import javax.inject.Inject


@HiltViewModel
class PengelolaanPerkuliahanViewModel @Inject constructor() : ViewModel()
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
    private val _list: MutableLiveData<List< >> =
        MutableLiveData()
    val list: LiveData<List< >> get() = _list
    suspend fun loadItems()
    {
        _isLoading.postValue(true)
        Repository.loadItems(onSuccess = {
            _isLoading.postValue(false)
            _list.postValue(it)
        }, onError = { list, message ->
            _toast.postValue(message)
            _isLoading.postValue(false)
            _list.postValue(list)
        })
    }
    suspend fun insert(tanggal: String,
                       nama: String,
                       berat: String){
        _isLoading.postValue(true)
        Repository.insert(tanggal, nama, berat,
            onError = { item, message ->
                _toast.postValue(message)
                _isLoading.postValue(false)
            }, onSuccess = {
                _isLoading.postValue(false)
                _success.postValue(true)
            })
    }
    suspend fun loadItem(id: String, onSuccess: (?) -> Unit) {
        val item = Repository.find(id)
        onSuccess(item)
    }
    suspend fun update(id: String,
                       matakuliah: String,
                       nama: String,
                       tanggal: String){
        _isLoading.postValue(true)
        Repository.update(id, matakuliah, nama, tanggal,
            onError = { item, message ->
                _toast.postValue(message)
                _isLoading.postValue(false)
            }, onSuccess = {
                _isLoading.postValue(false)
                _success.postValue(true)
            })
    }

}