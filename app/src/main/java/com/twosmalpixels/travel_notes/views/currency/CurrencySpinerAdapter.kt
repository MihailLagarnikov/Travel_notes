package com.twosmalpixels.travel_notes.views.currency

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.twosmalpixels.travel_notes.R

class CurrencySpinerAdapter(
    context: Context,
    val resource: Int,
    val listData: ArrayList<CurrencyData>
) :
    ArrayAdapter<String>(context, resource, Array(listData.size, { "" })),
    CurrencySpinnerListener {


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
        typeCurrency.setText(listData.get(position).hint)
        if (listData.get(position).currencyResourceText == 0) {
            curency.setText(listData.get(position).currencyText)
        } else {
            val text1 = context.getString(listData.get(position).currencyResourceText)
            val text = listData.get(position).currencyIso + " (" + text1 + ")"
            curency.setText(text)
        }

        if (position == 0) {
            typeCurrency.visibility = View.GONE
            curency.setTextColor(context.resources.getColor(R.color.text_979))
            curency.setText(listData.get(position).hint)
            val params = curency.layoutParams as ConstraintLayout.LayoutParams
            params.topMargin = params.leftMargin
            curency.layoutParams = params
        }
        if (position == listData.size - 1) {
            view.findViewById<ImageView>(R.id.curremcy_spiner_divider).visibility = View.VISIBLE
            typeCurrency.visibility = View.GONE
            curency.setTextColor(context.resources.getColor(R.color.text_979))
            curency.setText(R.string.choose_anoter_cur)
            val params = curency.layoutParams as ConstraintLayout.LayoutParams
            params.bottomMargin = params.leftMargin
            params.topMargin = params.leftMargin
            curency.layoutParams = params
        }
        return view
    }

    override fun getSelectedDate(position: Int): CurrencyData {
        return listData.get(position)
    }

}