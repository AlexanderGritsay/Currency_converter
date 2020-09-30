package com.example.converter.domain

import android.os.Parcelable
import androidx.lifecycle.Transformations.map
import androidx.room.Database
import androidx.room.PrimaryKey
import com.example.converter.database.DatabaseCurrency
import kotlinx.android.parcel.Parcelize

// These are the objects that should be displayed on screen, or manipulated by the app.
@Parcelize
data class CurrencyModel constructor(
    val id: String,
    val numCode: String,
    val charCode: String,
    val nominal: Int,
    val name: String,
    val value: Double,
    val previous: Double
) : Parcelable
