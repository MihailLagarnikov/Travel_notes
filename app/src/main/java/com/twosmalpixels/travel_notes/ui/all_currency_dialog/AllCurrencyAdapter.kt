package com.twosmalpixels.travel_notes.ui.all_currency_dialog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.views.currency.CurrencyData
import kotlinx.android.synthetic.main.all_currency_item.view.*

class AllCurrencyAdapter(val listData: ArrayList<CurrencyData>, val listener: (CurrencyData, Int) -> Unit): RecyclerView.Adapter<AllCurrencyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.all_currency_item, parent, false))
    }

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.currency_text_item.setText(listData.get(position).currencyText)
        holder.itemView.setOnClickListener {
            //мы выбрали валюту
            listener.invoke(listData.get(position), position)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}