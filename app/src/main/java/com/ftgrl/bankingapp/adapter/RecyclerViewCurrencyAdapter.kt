package com.ftgrl.bankingapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ftgrl.bankingapp.databinding.RowCurrencyBinding
import com.ftgrl.bankingapp.model.CurrencyItem

class RecyclerViewCurrencyAdapter(
    private val currencyList: List<CurrencyItem>,
    private val listener: Listener
) : RecyclerView.Adapter<RecyclerViewCurrencyAdapter.RowHolder>() {

    interface Listener {
        fun onItemClick(currencyItem: CurrencyItem)
    }

    class RowHolder(val binding: RowCurrencyBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val binding = RowCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RowHolder(binding)
    }

    override fun getItemCount(): Int {
        return currencyList.size
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        val currencyItem = currencyList[position]

        holder.itemView.setOnClickListener {
            listener.onItemClick(currencyItem)
        }

        holder.binding.currencyNameText.text = currencyItem.name
        holder.binding.priceText.text = currencyItem.value.toString()
    }
}
