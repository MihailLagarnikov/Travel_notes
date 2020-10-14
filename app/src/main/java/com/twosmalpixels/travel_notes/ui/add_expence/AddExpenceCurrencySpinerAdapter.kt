package com.twosmalpixels.travel_notes.ui.add_expence

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.views.currency.CurrencyData
import com.twosmalpixels.travel_notes.views.currency.CurrencySpinnerListener

class AddExpenceCurrencySpinerAdapter(
    context: Context,
    val resource: Int,
    val listData: ArrayList<CurrencyData>
) :
    ArrayAdapter<String>(context, resource, Array(listData.size, { "" })), CurrencySpinnerListener {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getMyView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getMyView(position, convertView, parent)
    }

    fun getMyView(position: Int, view: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(parent.context).inflate(resource, parent, false)
        val typeCurrency = view.findViewById<TextView>(R.id.type_currency)
        val curency = view.findViewById<TextView>(R.id.currency_text)
        typeCurrency.visibility = View.GONE
        curency.text = listData.get(position).currencyIso
        return view
    }

    override fun getSelectedDate(position: Int): CurrencyData {
        return listData.get(position)
    }

}