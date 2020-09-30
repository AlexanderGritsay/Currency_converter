package com.example.converter.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.example.converter.database.getDatabase
import com.example.converter.domain.CurrencyModel
import com.example.converter.repository.CurrenciesRepository
import kotlinx.coroutines.*
import java.lang.Exception

enum class CurrenciesApiStatus { LOADING, ERROR, DONE }

class CurrencyListViewModel(application: Application) : AndroidViewModel(application) {

    private val job = Job()
    private val uiScope = CoroutineScope(job + Dispatchers.Main)

    private val database = getDatabase(application)
    private val currenciesRepository = CurrenciesRepository(database)

    val currencies = currenciesRepository.currencies

    private val _status = MutableLiveData<CurrenciesApiStatus>()
    val status: LiveData<CurrenciesApiStatus>
        get() = _status

    init {
        refreshCurrencies()
    }

    private val _navigateToConverter = MutableLiveData<CurrencyModel>()
    val navigateToConverter: LiveData<CurrencyModel>
        get() = _navigateToConverter

    fun onNavigationDone() {
        _navigateToConverter.value = null
    }

    private val _isRefreshed = MutableLiveData<Boolean>()
    val isRefreshed: LiveData<Boolean>
        get() = _isRefreshed

    fun onRefreshed() {
        _isRefreshed.value = null
        _status.value = CurrenciesApiStatus.DONE
    }


    fun refreshCurrencies() {
        uiScope.launch {
//            if (currencies.value == null) {
//                _status.value = CurrenciesApiStatus.LOADING
//            }
            try {
                currenciesRepository.refreshCurrencies()
//                _status.value = CurrenciesApiStatus.DONE
            } catch (exception: Exception) {
                _status.value = CurrenciesApiStatus.ERROR
            }
            _isRefreshed.value = true
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun onCurrencyConverClicked(currencyModel: CurrencyModel) {
        _navigateToConverter.value = currencyModel
    }


    /**
     * Factory for constructing DevByteViewModel with parameter
     */
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CurrencyListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CurrencyListViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
