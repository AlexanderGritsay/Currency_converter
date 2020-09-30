package com.example.converter.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.converter.database.CurrenciesDatabase
import com.example.converter.database.asDomainModel
import com.example.converter.domain.CurrencyModel
import com.example.converter.network.Network
import com.example.converter.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CurrenciesRepository(private val database: CurrenciesDatabase) {

    val currencies: LiveData<List<CurrencyModel>> =
        Transformations.map(database.currencyDao.getCurrencies()) {
            it.asDomainModel()

        }


    suspend fun refreshCurrencies() {
        withContext(Dispatchers.IO) {
            val todayData = Network.currencies.getTodayData().await()
            database.currencyDao.insertAll(*todayData.valute.asDatabaseModel())
        }
    }
}