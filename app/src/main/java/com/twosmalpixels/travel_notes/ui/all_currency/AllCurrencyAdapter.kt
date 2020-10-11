package com.twosmalpixels.travel_notes.ui.all_currency

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.views.currency.CurrencyData
import kotlinx.android.synthetic.main.all_currency_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class AllCurrencyAdapter(var listData: ArrayList<CurrencyData>, val listener: (CurrencyData, Int) -> Unit): RecyclerView.Adapter<AllCurrencyAdapter.ViewHolder>() {

        val firstList = listData;

    fun sortList(searchText: String){
        listData = firstList.filter{ it.currencyText.toLowerCase(Locale.getDefault()).contains(searchText.toLowerCase(Locale.getDefault()))
                    || it.currencyIso.toLowerCase(Locale.getDefault()).contains(searchText.toLowerCase(Locale.getDefault()))
                    || it.countriCode.toLowerCase(Locale.getDefault()).contains(searchText.toLowerCase(Locale.getDefault()))} as ArrayList<CurrencyData>
        notifyDataSetChanged()
    }

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