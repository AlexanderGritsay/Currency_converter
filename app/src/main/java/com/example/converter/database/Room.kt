package com.example.converter.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.converter.domain.CurrencyModel


@Dao
interface CurrencyDao {

    @Query("select * from databasecurrency")
    fun getCurrencies(): LiveData<List<DatabaseCurrency>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg currencies: DatabaseCurrency)
}

@Database(entities = [DatabaseCurrency::class], version = 1)
abstract class CurrenciesDatabase : RoomDatabase() {

    abstract val currencyDao: CurrencyDao
}

@Volatile
private lateinit var INSTANCE: CurrenciesDatabase

fun getDatabase(context: Context): CurrenciesDatabase {
    synchronized(CurrenciesDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                CurrenciesDatabase::class.java,
                "currencies").fallbackToDestructiveMigration().build()
        }
        return INSTANCE
    }
}