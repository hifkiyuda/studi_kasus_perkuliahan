package id.ac.unpas.composeperkuliahankelompok5.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ac.unpas.composeperkuliahankelompok5.model.Mahasiswa
import id.ac.unpas.composeperkuliahankelompok5.repositories.MahasiswaRepository
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PengelolaanMahasiswaViewModel @Inject constructor(private val mahasiswaRepository: MahasiswaRepository) : ViewModel()
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
    private val _list: MutableLiveData<List<Mahasiswa>> =
        MutableLiveData()
    val list: LiveData<List<Mahasiswa>> get() = _list
    suspend fun loadItems()
    {
        _isLoading.postValue(true)
        mahasiswaRepository.loadItems(onSuccess = {
            _isLoading.postValue(false)
            _list.postValue(it)
        }, onError = { list, message ->
            _toast.postValue(message)
            _isLoading.postValue(false)
            _list.postValue(list)
        })
    }
    suspend fun insert(npm: String,
                       nama: String,
                       tanggalLahir: Date,
                       jenisKelamin: Mahasiswa.JenisKelamin){
        _isLoading.postValue(true)
        mahasiswaRepository.insert(npm, nama, tanggalLahir, jenisKelamin,
            onError = { item, message ->
                _toast.postValue(message)
                _isLoading.postValue(false)
            }, onSuccess = {
                _isLoading.postValue(false)
                _success.postValue(true)
            })
    }
    suspend fun loadItem(id: String, onSuccess: (Mahasiswa?) -> Unit) {
        val item = mahasiswaRepository.find(id)
        onSuccess(item)
    }
    suspend fun update(id: String,
                       npm: String,
                       nama: String,
                       tanggalLahir: Date,
                       jenisKelamin: Mahasiswa.JenisKelamin){
        _isLoading.postValue(true)
        mahasiswaRepository.update(id, npm, nama, tanggalLahir, jenisKelamin,
            onError = { item, message ->
                _toast.postValue(message)
                _isLoading.postValue(false)
            }, onSuccess = {
                _isLoading.postValue(false)
                _success.postValue(true)
            })
    }
}