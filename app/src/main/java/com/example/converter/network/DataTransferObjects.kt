package com.example.converter.network

import com.example.converter.database.DatabaseCurrency
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class TodayData(
    @Json(name = "Date")
    val date: String,

    @Json(name = "PreviousDate")
    val previousDate: String,

    @Json(name = "PreviousURL")
    val previousURL: String,

    @Json(name = "Timestamp")
    val timestamp: String,

    @Json(name = "Valute")
    val valute: NetworkValute
)

@JsonClass(generateAdapter = true)
data class NetworkValute(

    @Json(name = "AUD")
    val aUD: NetworkCurrency,

    @Json(name = "AZN")
    val aZN: NetworkCurrency,

    @Json(name = "GBP")
    val gBP: NetworkCurrency,

    @Json(name = "AMD")
    val aMD: NetworkCurrency,

    @Json(name = "BYN")
    val bYN: NetworkCurrency,

    @Json(name = "BGN")
    val bGN: NetworkCurrency,

    @Json(name = "BRL")
    val bRL: NetworkCurrency,

    @Json(name = "HUF")
    val hUF: NetworkCurrency,

    @Json(name = "HKD")
    val hKD: NetworkCurrency,

    @Json(name = "DKK")
    val dKK: NetworkCurrency,

    @Json(name = "USD")
    val uSD: NetworkCurrency,

    @Json(name = "EUR")
    val eUR: NetworkCurrency,

    @Json(name = "INR")
    val iNR: NetworkCurrency,

    @Json(name = "KZT")
    val kZT: NetworkCurrency,

    @Json(name = "CAD")
    val cAD: NetworkCurrency,

    @Json(name = "KGS")
    val kGS: NetworkCurrency,

    @Json(name = "CNY")
    val cNY: NetworkCurrency,

    @Json(name = "MDL")
    val mDL: NetworkCurrency,

    @Json(name = "NOK")
    val nOK: NetworkCurrency,

    @Json(name = "PLN")
    val pLN: NetworkCurrency,

    @Json(name = "RON")
    val rON: NetworkCurrency,

    @Json(name = "XDR")
    val xDR: NetworkCurrency,

    @Json(name = "SGD")
    val sGD: NetworkCurrency,

    @Json(name = "TJS")
    val tJS: NetworkCurrency,

    @Json(name = "TRY")
    val tRY: NetworkCurrency,

    @Json(name = "TMT")
    val tMT: NetworkCurrency,

    @Json(name = "UZS")
    val uZS: NetworkCurrency,

    @Json(name = "UAH")
    val uAH: NetworkCurrency,

    @Json(name = "CZK")
    val cZK: NetworkCurrency,

    @Json(name = "SEK")
    val sEK: NetworkCurrency,

    @Json(name = "CHF")
    val cHF: NetworkCurrency,

    @Json(name = "ZAR")
    val zAR: NetworkCurrency,

    @Json(name = "KRW")
    val kRW: NetworkCurrency,

    @Json(name = "JPY")
    val jPY: NetworkCurrency
)

@JsonClass(generateAdapter = true)
class NetworkCurrency(
    @Json(name = "ID")
    val id: String,

    @Json(name = "NumCode")
    val numCode: String,

    @Json(name = "CharCode")
    val charCode: String,

    @Json(name = "Nominal")
    val nominal: Int,

    @Json(name = "Name")
    val name: String,

    @Json(name = "Value")
    val value: Double,

    @Json(name = "Previous")
    val previous: Double
)

fun NetworkValute.asDatabaseModel(): Array<DatabaseCurrency> {
    val valuteList = listOf<NetworkCurrency>(
    aUD,
    aZN,
    aMD,
    bGN,
    bRL,
    bYN,
    cAD,
    cHF,
    cNY,
    cZK,
    dKK,
    gBP,
    hKD,
    hUF,
    iNR,
    jPY,
    kGS,
    kRW,
    kZT,
    mDL,
    nOK,
    pLN,
    rON,
    sEK,
    sGD,
    tJS,
    tMT,
    tRY,
    uAH,
    uSD,
    uZS,
    xDR,
    zAR
    )
    return valuteList.map {
        DatabaseCurrency(
            id = it.id,
            numCode = it.numCode,
            charCode = it.charCode,
            nominal = it.nominal,
            name = it.name,
            value = it.value,
            previous = it.previous
        )
    }.toTypedArray()
}

