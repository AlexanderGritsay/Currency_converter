package com.example.converter.ui

import android.renderscript.ScriptGroup
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.converter.databinding.ItemCurrencyBinding
import com.example.converter.domain.CurrencyModel
import java.util.*

class CurrencyListAdapter(val callback: ExchangeClick) :
    ListAdapter<CurrencyModel, CurrencyListAdapter.CurrencyViewHolder>(CurrencyDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        return CurrencyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(callback, getItem(position))
    }

    class CurrencyViewHolder private constructor(val binding: ItemCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: ExchangeClick, item: CurrencyModel) {
            binding.currency = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): CurrencyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCurrencyBinding.inflate(layoutInflater, parent, false)
                return CurrencyViewHolder(binding)
            }
        }
    }
}

class CurrencyDiffCallback : DiffUtil.ItemCallback<CurrencyModel>() {
    override fun areItemsTheSame(oldItem: CurrencyModel, newItem: CurrencyModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CurrencyModel, newItem: CurrencyModel): Boolean {
        return oldItem == newItem
    }

}

class ExchangeClick(val clickListener: (Currency: CurrencyModel) -> Unit) {
    fun onClick(currency: CurrencyModel) = clickListener(currency)
}
