package com.example.converter

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.converter.domain.CurrencyModel
import com.example.converter.ui.CurrencyListAdapter

import com.example.converter.viewmodels.CurrenciesApiStatus


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<CurrencyModel>?) {
    (recyclerView.adapter as CurrencyListAdapter).submitList(data)
}

@BindingAdapter("flagImage")
fun bindImage(imgView: ImageView, currecyCharCode: String?) {
    imgView.setImageResource(
        when (currecyCharCode) {
            "AUD" -> R.drawable.aud
            "AZN" -> R.drawable.azn
            "AMD" -> R.drawable.amd
            "BGN" -> R.drawable.bgn
            "BRL" -> R.drawable.brl
            "BYN" -> R.drawable.byn
            "CAD" -> R.drawable.cad
            "CHF" -> R.drawable.chf
            "CNY" -> R.drawable.cny
            "CZK" -> R.drawable.czk
            "DKK" -> R.drawable.dkk
            "GBP" -> R.drawable.gbp
            "HKD" -> R.drawable.hkd
            "HUF" -> R.drawable.huf
            "INR" -> R.drawable.inr
            "JPY" -> R.drawable.jpy
            "KGS" -> R.drawable.kgs
            "KRW" -> R.drawable.krw
            "KZT" -> R.drawable.kzt
            "MDL" -> R.drawable.mdl
            "NOK" -> R.drawable.nok
            "PLN" -> R.drawable.pln
            "RON" -> R.drawable.ron
            "SEK" -> R.drawable.sek
            "SGD" -> R.drawable.sgd
            "TJS" -> R.drawable.tjs
            "TMT" -> R.drawable.tmt
            "TRY" -> R.drawable.try1
            "UAH" -> R.drawable.uah
            "USD" -> R.drawable.usd
            "UZS" -> R.drawable.uzs
            "XDR" -> R.drawable.xdr
            "ZAR" -> R.drawable.zar
            "RUB" -> R.drawable.rub
            else -> R.drawable.ic_broken_image
        }
    )

}

@BindingAdapter("currencyApiStatus")
fun bindStatus(statusImageView: ImageView, status: CurrenciesApiStatus?) {
    when (status) {
        CurrenciesApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        CurrenciesApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

