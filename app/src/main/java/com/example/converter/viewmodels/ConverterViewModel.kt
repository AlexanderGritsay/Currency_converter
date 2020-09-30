package com.example.converter.viewmodels

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.converter.domain.CurrencyModel

class ConverterViewModel(val foreignCorrency: CurrencyModel) : ViewModel() {

    private val _convertClicked = MutableLiveData<Boolean>()
    val convertClicked: LiveData<Boolean>
        get() = _convertClicked

    private val _convertedSum = MutableLiveData<Double>()
    val convertedSum: LiveData<Double>
        get() = _convertedSum

    init {
        _convertedSum.value = 0.toDouble()
    }

    fun convertIsClicked() {
        _convertClicked.value = true
    }

    fun onConvertAlreadyClicked() {
        _convertClicked.value = false
    }

    fun convert(text: Editable) {

        if (!text.toString().isNullOrEmpty()) {
            val sumToConvert = text.toString().toLong()
//            if (sumToConvert>10000)
            _convertedSum.value = sumToConvert * foreignCorrency.nominal.toLong()/ foreignCorrency.value.toDouble()
        } else {
            _convertedSum.value = 0.toDouble()
        }
    }


    class Factory(
        private val foreignCorrency: CurrencyModel
    ) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ConverterViewModel::class.java)) {
                return ConverterViewModel(foreignCorrency) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}