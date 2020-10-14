package com.twosmalpixels.travel_notes.core.extension

import android.net.Uri
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import com.twosmalpixels.travel_notes.views.currency.CurrencyData
import com.twosmalpixels.travel_notes.views.currency.CurrencySpinnerListener
import com.twosmalpixels.travel_notes.views.currency.getEmptyCurrencyData

fun View.setVisibility(visibility: Boolean, isGone: Boolean = true){
    this.visibility = if (visibility){
        View.VISIBLE
    }else if (isGone){
        View.GONE
    }else{
        View.INVISIBLE
    }
}

fun TextView.setNonEmptyText(text: String?){
    val invisibility = text == null || text.isEmpty()
    if (!invisibility){
        this.setText(text)
    }
    this.setVisibility(!invisibility)
}

fun Map<String, Any>.getFireString(key: String): String{
    if (!this.containsKey(key)) return ""
    return this.get(key).toString()
}

fun Map<String, Any>.getFireLong(key: String): Long{
    if (!this.containsKey(key)) return 0L
    return this.get(key) as Long
}

fun Map<String, Any>.getFireDouble(key: String): Double{
    if (!this.containsKey(key)) return 0.0
    return this.get(key) as Double
}

fun Map<String, Any>.getFireInt(key: String): Int{
    if (!this.containsKey(key)) return 0
    return (this.get(key) as Long).toInt()
}

fun ImageView.setUri(uri: Uri){
    this.setImageBitmap(MediaStore.Images.Media.getBitmap(this.context.contentResolver, uri))
}

fun Spinner.getSelectedCurrencyData(): CurrencyData{
    if (this.adapter is CurrencySpinnerListener){
        return (this.adapter as CurrencySpinnerListener).getSelectedDate(this.selectedItemPosition)
    }else{
        return getEmptyCurrencyData()
    }
}

fun Spinner.getCurrencyData(position: Int): CurrencyData{
    if (this.adapter is CurrencySpinnerListener){
        return (this.adapter as CurrencySpinnerListener).getSelectedDate(position)
    }else{
        return getEmptyCurrencyData()
    }
}

fun Spinner.equalsCurrency(anotherSpiner: Spinner, position: Int = 500): Boolean{
    return if (position == 500){
        this.getSelectedCurrencyData().currencyIso.equals(anotherSpiner.getSelectedCurrencyData().currencyIso)
    }else{
        this.getSelectedCurrencyData().currencyIso.equals(anotherSpiner.getCurrencyData(position).currencyIso)
    }
}