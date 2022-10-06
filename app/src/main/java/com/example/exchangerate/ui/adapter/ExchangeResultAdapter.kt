package com.example.exchangerate.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangerate.databinding.ItemExchangeRateBinding
import com.example.exchangerate.model.Currency

class ExchangeResultAdapter : ListAdapter<Currency, ExchangeResultAdapter.ViewHolder>(
    DIFF_UTIL
) {

    inner class ViewHolder(private val binding: ItemExchangeRateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currency: Currency) {
            with(currency) {
                binding.apply {
//                    baseCurrency?.let {
//                        tvExchangeResult.text = (amount * rateInUsd/it.rateInUsd).toString()
//                    }
                    tvExchangeResult.text = rateInUsd.toBigDecimal().toPlainString()
                    tvCurrencyId.text = id
                    tvCurrencyName.text = name
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return ViewHolder(ItemExchangeRateBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Currency>() {
            override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean {
                return oldItem == newItem
            }
        }
    }
}