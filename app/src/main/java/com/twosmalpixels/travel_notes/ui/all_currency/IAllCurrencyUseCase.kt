package com.twosmalpixels.travel_notes.ui.all_currency

import com.twosmalpixels.travel_notes.views.currency.CurrencyData

interface IAllCurrencyUseCase {
    fun getAllCurrency(hint: Int): ArrayList<CurrencyData>
    fun getHint(isMainCurrency: Boolean): Int
}