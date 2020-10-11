package com.twosmalpixels.travel_notes.views.currency

data class CurrencyData(val hint: Int, val currencyText: String, val currencyIso: String, val countriCode: String, val currencyResourceText: Int = 0) {
}

fun getEmptyCurrencyData(): CurrencyData{
    return CurrencyData(0, "", "", "")
}