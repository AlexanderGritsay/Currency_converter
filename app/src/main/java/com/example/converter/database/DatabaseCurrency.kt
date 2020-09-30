package com.example.converter.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.converter.domain.CurrencyModel

@Entity
data class DatabaseCurrency constructor(
    @PrimaryKey
    val id: String,
    val numCode: String,
    val charCode: String,
    val nominal: Int,
    val name: String,
    val value: Double,
    val previous: Double
)

fun List<DatabaseCurrency>.asDomainModel(): List<CurrencyModel> {
    return map {
        CurrencyModel(
            id = it.id,
            numCode = it.numCode,
            charCode = it.charCode,
            nominal = it.nominal,
            name = it.name,
            value = it.value,
            previous = it.previous
        )
    }
}
